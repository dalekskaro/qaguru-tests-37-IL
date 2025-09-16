package lesson9.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.List;
import lesson9.utils.ZipUtils;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class HomeworkZipTest {

  private final ClassLoader classLoader = getClass().getClassLoader();
  String zipFile = "HungerGames.zip";


  @Test
  @DisplayName("Проверка, что созданный файл пуст")
  void emptyZipTest() throws IOException {
    ZipUtils zipUtils = new ZipUtils();

    File zipFile = new File(zipUtils.createEmptyZip("Empty"));
    assertTrue(zipFile.exists(), "Файл '" + zipFile + "' не найден");

    try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile))) {
      assertNull(zipInputStream.getNextEntry(), "Файл '" + zipFile + "' не пуст");
    }
  }

  @Test
  @Tag("homework-9")
  @DisplayName("Проверка текста в файле типа pdf, который находится в zip-архиве")
  void pfdInZipTest() throws Exception {
    String text = "As the day dawns on the 50th annual Hunger Games, fear grips the districts of Panem";
    InputStream inputStream = classLoader.getResourceAsStream("lesson9/" + zipFile);

    assertNotNull(inputStream, "Файл '" + zipFile + "' не найден в ресурсах");

    try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

      ZipEntry entry;

      boolean isZipEmpty = zipInputStream.getNextEntry() == null;

      assertFalse(isZipEmpty,"Файл '" + zipFile + "' пуст");

      while ((entry = zipInputStream.getNextEntry()) != null) {
        String fileInZipName = entry.getName();

        if (fileInZipName.contains("__MACOSX") || !fileInZipName.endsWith(".pdf")) {
          System.out.println("### Пропускаем файл: " + fileInZipName);
          continue;
        }

        PDF pdf = new PDF(zipInputStream);
        assertTrue(pdf.text.contains(text),
            "Файл '" + fileInZipName + "' с текстом внутри '" + pdf.text + "' не содержит искомого текста: " + text);
      }
    }
  }

  @Tag("homework-9")
  @ParameterizedTest(name = "В таблице есть значение для трибута {0}")
  @CsvSource(value = {
      "Lucy Gray Baird, 10, 1",
      "Haymitch Abernathy, 50, 2",
      "Katniss Everdeen, 74, 3",
      "Peeta Mellark, 74, 4"
  })
  void xlsxInZipTest(String name, Integer game, Integer row) throws Exception {
    InputStream inputStream = classLoader.getResourceAsStream("lesson9/" + zipFile);

    assertNotNull(inputStream, "Файл '" + zipFile + "' не найден в ресурсах");

    try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

      ZipEntry entry;

      boolean isZipEmpty = zipInputStream.getNextEntry() == null;

      assertFalse(isZipEmpty,"Файл '" + zipFile + "' пуст");

      while ((entry = zipInputStream.getNextEntry()) != null) {
        String fileInZipName = entry.getName();

        if (fileInZipName.contains("__MACOSX") || !fileInZipName.endsWith(".xlsx")) {
          System.out.println("### Пропускаем файл: " + fileInZipName);
          continue;
        }

        XLS xlsx = new XLS(zipInputStream);

        String nameValue = xlsx.excel.getSheetAt(0).getRow(row).getCell(0).getStringCellValue();
        Assertions.assertTrue(nameValue.contains(name),
            "В таблице '" + fileInZipName + "' ячейка со значением '"
                + nameValue + "' не содержит имени '" + name + "'");

        Integer gameValue = (int) xlsx.excel.getSheetAt(0).getRow(row).getCell(1).getNumericCellValue();
        Assertions.assertSame(game, gameValue, "В таблице '" + fileInZipName + "' ячейка со значением '"
            + gameValue + "' не содержит игры с номером '" + game + "'");
      }
    }
  }

  @Tag("homework-9")
  @ParameterizedTest(name = "В таблице есть значение для дистрикта {0}")
  @CsvSource(value = {
      "district, industry, population, 0",
      "District 1, Luxury items, 24315, 1",
      "District 4, Fishing, 111453, 2",
      "District 12, Coal mining, 7935, 3"
  })
  void csvInZipTest(String district, String industry, String population, Integer row) throws Exception {
    InputStream inputStream = classLoader.getResourceAsStream("lesson9/" + zipFile);

    assertNotNull(inputStream, "Файл '" + zipFile + "' не найден в ресурсах");

    try (ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

      ZipEntry entry;

      boolean isZipEmpty = zipInputStream.getNextEntry() == null;

      assertFalse(isZipEmpty,"Файл '" + zipFile + "' пуст");

      while ((entry = zipInputStream.getNextEntry()) != null) {
        String fileInZipName = entry.getName();

        if (fileInZipName.contains("__MACOSX") || !fileInZipName.toLowerCase().endsWith(".csv")) {
          System.out.println("### Пропускаем файл: " + fileInZipName);
          continue;
        }

        CSVReader csvReader = new CSVReader(new InputStreamReader(zipInputStream,
            Charset.forName("Windows-1251")));
        List<String[]> data = csvReader.readAll();
        Assertions.assertEquals(4, data.size(),
            "В файле '" + fileInZipName + "' количество строк не равно 4");
        Assertions.assertArrayEquals(new String[]{district, industry, population}, data.get(row),
            "В файле '" + fileInZipName + "' в строке " + row + " данные не совпадают");
      }
    }
  }
}
