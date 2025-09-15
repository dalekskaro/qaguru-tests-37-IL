package lesson10.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import io.qameta.allure.Step;

public class WebSteps {

  @Step("Открываем страницу {url}")
  public void openTextBox(String url){
    open(url);
  }

  @Step("Заполняем поле Name")
  public void setValueName(String name) {
    $("#userName").setValue(name);
  }

  @Step("Нажимаем на submit")
  public void clickBtn() {
    $("#submit").click();
  }

  @Step("Проверяем, что данные сохранились верно")
  public void checkName(String name) {
    $("#output #name").shouldHave(text(name));
  }

}
