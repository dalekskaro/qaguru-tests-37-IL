package lesson10.tests;

import static com.codeborne.selenide.Condition.text;
import com.codeborne.selenide.Configuration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.logevents.SelenideLogger;
import static io.qameta.allure.Allure.step;
import io.qameta.allure.selenide.AllureSelenide;
import lesson10.pages.TextBoxPage;
import lesson10.pages.WebSteps;
import org.junit.jupiter.api.BeforeAll;
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
  void annotation1Test() {
    TextBoxPage textBoxPage = new TextBoxPage();

    textBoxPage.openPage()
        .removeAdd()
        .setFullName("Sirius Black")
        .setEmail("atyd@hogwarts.owl")
        .setCurrentAddress("Gryffindor")
        .setPermanentAddress("Azkaban")
        .clickOnSubmitButton();

    textBoxPage.checkOutputValueName("Sirius Black")
        .checkOutputValueEmail("atyd@hogwarts.owl")
        .checkOutputValueCurrentAddress("Gryffindor")
        .checkOutputValuePermananetAddress("Azkaban");
  }
}
