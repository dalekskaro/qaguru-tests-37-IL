package lesson9.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DownloadUploadTest {
    @BeforeAll
    static void setUpBrowser() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void downloadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downloaded = $(".react-blob-header-edit-and-raw-actions [href*='main/README.md']").download();

        try (InputStream is = new FileInputStream(downloaded)) {
            byte[] data = is.readAllBytes();
            String dataAsString = new String(data, StandardCharsets.UTF_8);
            Assertions.assertTrue(dataAsString.contains("Contributions to JUnit 5 are both welcomed and appreciated"));
        }
    }

    @Test
    void uploadFileTest() {
        open("https://fineuploader.com/demos.html");
        $("input[type='file']").uploadFromClasspath("cat.png");
        $(".qq-file-name").shouldHave(text("cat.png"));
    }
}
