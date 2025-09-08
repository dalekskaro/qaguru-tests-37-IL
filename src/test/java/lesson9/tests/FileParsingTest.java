package lesson9.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.selenide.Configuration;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import lesson9.model.Glossary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileParsingTest {

    private ClassLoader classLoader = FileParsingTest.class.getClassLoader();
    private static final Gson gson = new Gson();

    @BeforeAll
    static void setUpBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void pdfFileParsingTest() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href*='junit-user-guide-5.13.4.pdf']").download();
        PDF pdf = new PDF(downloaded);
        System.out.println(pdf);
        Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
    }

    @Test
    void xlsFileParsingTest() {
        open("https://excelvba.ru/programmes/Teachers?ysclid=lfcu77j9j9951587711");
        File downloaded = $("[href='https://ExcelVBA.ru/sites/default/files/teachers.xls']").download();
        XLS xls = new XLS(downloaded);

        System.out.println(xls);
        String actualValue = xls.excel.getSheetAt(0).getRow(3).getCell(2).getStringCellValue();

        Assertions.assertTrue(actualValue.contains("Суммарное количество часов планируемое на штатную по всем разделам"));
    }

    @Test
    void csvFileParsingTest() throws Exception {
        try (InputStream inputStream = classLoader.getResourceAsStream("lesson9/example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {

            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(new String[] {"Selenide", "https://selenide.org"}, data.get(0));
            Assertions.assertArrayEquals(new String[] {"JUnit 5","https://junit.org"}, data.get(1));
        }
    }

    @Test
    void zipFileParsingTest() throws Exception {
        try (ZipInputStream zipInputStream = new ZipInputStream(
                classLoader.getResourceAsStream("lesson9/example.zip")
        )) {
            ZipEntry entry;

            while ((entry = zipInputStream.getNextEntry()) != null) {
                System.out.println("zipName: " + entry.getName());
            }
        }
    }

    @Test
    void jsonFileParsingTest() throws Exception {
        try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("lesson9/example.json")
        )) {
            JsonObject actual = gson.fromJson(reader, JsonObject.class);
            Assertions.assertEquals("example glossary", actual.get("title").getAsString());
            Assertions.assertEquals(234234, actual.get("ID").getAsInt());

            JsonObject inner = actual.get("glossary").getAsJsonObject();
            Assertions.assertEquals("SGML", inner.get("SortAs").getAsString());
            Assertions.assertEquals("Standard Generalized Markup Language", inner.get("GlossTerm").getAsString());
        }
    }

    @Test
    void jsonFileParsingImprovedTest() throws Exception {
        try (Reader reader = new InputStreamReader(classLoader.getResourceAsStream("lesson9/example.json"))) {
            Glossary actual = gson.fromJson(reader, Glossary.class);

            Assertions.assertEquals("example glossary", actual.getTitle());
            Assertions.assertEquals(234234, actual.getID());
            Assertions.assertEquals("SGML", actual.getGlossary().getSortAs());
            Assertions.assertEquals("Standard Generalized Markup Language", actual.getGlossary().getGlossTerm());
        }
    }
}
