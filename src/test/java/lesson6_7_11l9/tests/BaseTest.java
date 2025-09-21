package lesson6_7_11l9.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import java.util.Map;
import lesson6_7_11l9.helpers.Attach;
import lesson6_7_11l9.utils.FileClean;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class BaseTest {

  @BeforeAll
  @Step("Устанавливаем настройки браузера")
  static void setUpBrowser() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browser = System.getProperty("browser");
    Configuration.browserVersion = System.getProperty("browserVersion");
    Configuration.browserSize = System.getProperty("browserResolution", "1920x1080");
    Configuration.pageLoadStrategy = "eager";

    Configuration.remote = System.getProperty("remote");
  }

  @BeforeAll
  @Step("Добавляем возможность записи видео")
  static void setVideo() {
    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
        "enableVNC", true,
        "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;
  }

  @BeforeEach
  @Step("Добавляем Allure Listener")
  void addListenerAllure() {
    SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
  }

  @AfterEach
  @Step("Закрываем драйвер браузера")
  void closeBrowserDriver() {
    closeWebDriver();
  }

  @AfterEach
  @Step("Очищаем временную папку")
  void cleanUpTmpFolder() {
    FileClean.cleanTmpFolder();
  }

  @AfterEach
  @Step("Добавляем вложения в отчет")
  void addAttachments() {
    Attach.screenshotAs("Скрин страницы");
    Attach.addVideo("Видео всего теста");
    Attach.pageSnapshot("Снапшот страницы");
    Attach.pageSource("Source страницы");
    Attach.browserConsoleLogs("Логи браузера");
  }
}
