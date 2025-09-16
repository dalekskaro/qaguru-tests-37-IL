package lesson10.tests;

import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class HomeworkAllureBaseTest {
  @BeforeAll
  static void setUpBrowser() {
    Configuration.baseUrl = "https://github.com/";
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "eager";
  }

  @AfterEach
  void closeBrowserDriver() {
    closeWebDriver();
  }

}
