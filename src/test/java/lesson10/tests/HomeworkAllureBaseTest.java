package lesson10.tests;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class HomeworkAllureBaseTest {
  @BeforeAll
  static void setUpBrowser() {
    Configuration.baseUrl = "https://github.com/";
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "eager";
  }

  @BeforeEach
  void addListenerForAllure() {
    SelenideLogger.addListener("allure", new AllureSelenide());
  }

  @AfterEach
  void closeBrowserDriver() {
    closeWebDriver();
  }

}
