package lesson9.tests;

import com.codeborne.pdftest.PDF;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import lesson9.utils.ZipUtils;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class PdfDebugTests {
  private final ClassLoader classLoader = getClass().getClassLoader();
  ZipUtils zipUtils = new ZipUtils();

  @AfterEach
  void cleanUp() {
    ZipUtils.cleanTmpFolder();
  }

  @Test
  @DisplayName("Проверяем, что можем запаковать в архив пдф")
  void zipPdfTest() throws Exception {
    String resourceName = "Sunrise on the Reaping.pdf";
    String zipName = "dove";
    zipUtils.createZipWithOneFile(resourceName, zipName);
  }

  @ParameterizedTest(name = "Проверка, что тест вообще видит пдф {0}")
  @CsvSource(value = {
      "example.pdf, ample PDF Content",
      "Sunrise on the Reaping.pdf, Sun"
  })
  void pdfTest(String resource, String text) throws IOException {
    PDF pdf = new PDF(classLoader.getResourceAsStream("lesson9/" + resource));
    System.out.println("pdf:" + pdf.text);
    assertTrue(pdf.text.contains(text));
  }

  @ParameterizedTest(name = "Проверка, что тест вообще видит пдф {0}")
  @CsvSource(value = {
      "examplePDF.zip, example.pdf, ample PDF Content",
      "Sunrise on the Reaping.zip, Sunrise on the Reaping.pdf, Sun"
  })
  void pfdInZipAlready(String zipName, String resource, String text) throws Exception {
    try (ZipInputStream zipInputStream = new ZipInputStream(
        classLoader.getResourceAsStream("lesson9/"  + zipName)
    )) {
      ZipEntry entry;

      while ((entry = zipInputStream.getNextEntry()) != null) {
        if (entry.getName().contains("__MACOSX")) {
          System.out.println("### Пропущено, так как содержит __MACOSX");
        }
        System.out.println("### zipName: " + entry.getName());
        PDF pdf = new PDF(zipInputStream);
        System.out.println("##### текст в пдф: " + pdf.text);
        System.out.println("##### содержит?: " + pdf.text.contains(text));
        assertTrue(pdf.text.contains(text));
      }
    }
  }
}
