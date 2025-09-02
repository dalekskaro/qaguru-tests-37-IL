package lesson8.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public abstract class BaseTestForCalculator {

    @BeforeAll
    static void setUpBrowser() {
        Configuration.baseUrl = "https://testsheepnz.github.io";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
    }

    @AfterEach
    void closeBrowserDriver() {
        closeWebDriver();
    }
}
