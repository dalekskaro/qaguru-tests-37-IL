package lesson10.tests;

import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.webdriver;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Allure;
import static io.qameta.allure.Allure.attachment;
import static io.qameta.allure.Allure.step;
import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import java.nio.charset.StandardCharsets;
import lesson10.pages.WebSteps;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class LessonTests {

  private static final String URL = "/text-box";
  private static final String NAME = "Strahd von Zarovich";

  @BeforeAll
  static void setUpBrowser() {
    Configuration.baseUrl = "https://demoqa.com";
    Configuration.browserSize = "1920x1080";
    Configuration.pageLoadStrategy = "eager";
  }

  @Test
  public void lambdaTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    step("Открываем страницу " + URL, () -> {
      open(URL);
      attachment("Source", webdriver().driver().source());
    });

    step("Заполняем поля данными", () -> {
      $("#userName").setValue("Strahd von Zarovich");
      $("#userEmail").setValue("strahd@barovia.dom");
      $("#currentAddress").setValue("Ravenloft, Barovia");
      $("#permanentAddress").setValue("Amber Temple, Barovia");
    });

    step("Кликаем на сохранить", () -> {
      $("#submit").click();
    });

    step("Проверяем, что данные сохранились верно", () -> {
      $("#output #name").shouldHave(text("Strahd von Zarovich"));
      $("#output #email").shouldHave(text("strahd@barovia.dom"));
      $("#output #currentAddress").shouldHave(text("Ravenloft, Barovia"));
      $("#output #permanentAddress").shouldHave(text("Amber Temple, Barovia"));
    });
  }

  @Test
  public void annotationTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    WebSteps steps = new WebSteps();

    steps.openTextBox(URL);
    steps.setValueName(NAME);
    steps.clickBtn();
    steps.checkName(NAME);
  }

  @Test
  public void lambdaAttachmentsTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    step("Открываем страницу " + URL, () -> {
      open(URL);
    });

    step("Достаем source страницы", () -> {
      attachment("Source", webdriver().driver().source());
    });

    step("Достаем снапшот страницы", () -> {
      Allure.getLifecycle().addAttachment(
          "Исходники страницы",
          "text/html",
          "html",
          WebDriverRunner.getWebDriver().getPageSource().getBytes(StandardCharsets.UTF_8)
      );
    });
  }

  @Test
  public void annotationAttachmentsTest() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    WebSteps steps = new WebSteps();

    steps.openTextBox(URL);
    steps.takeScreenshot();
  }

  @Test
  @Feature("Форма Test Box")
  @Story("Заполнение формы")
  @Owner("attano")
  @Severity(SeverityLevel.BLOCKER)
  @Link(value = "Testing", url = "https://demoqa.com/text-box")
  @DisplayName("да вы посмотрите на эти аннотации")
  public void staticLabelsTest() {
  }

  @Test
  public void dynamicLabelsTest() {
    Allure.getLifecycle().updateTestCase(
        t -> t.setName("да вы посмотрите на эти аннотации")
    );
    Allure.feature("Форма Test Box");
    Allure.story("Заполнение формы");
    Allure.label("owner", "attano");
    Allure.label("severity", SeverityLevel.CRITICAL.value());
    Allure.link("Testing", "https://demoqa.com/text-box");
  }
}
