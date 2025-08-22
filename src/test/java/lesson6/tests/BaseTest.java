package lesson6.tests;

import com.codeborne.selenide.Configuration;
import lesson6.pages.StudentRegistrationPage;
import lesson6.pages.TextBoxPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {
    StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();
    TextBoxPage textBoxPage = new TextBoxPage();

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
}
