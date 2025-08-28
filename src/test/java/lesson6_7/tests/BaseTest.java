package lesson6_7.tests;

import com.codeborne.selenide.Configuration;
import lesson6_7.utils.FileClean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTest {

    @BeforeAll
    static void setUpBrowser() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void closeBrowserDriver() {
        closeWebDriver();
    }

    @AfterEach
    void cleanUpTmpFolder() {
        FileClean.cleanTmpFolder();
    }
}
