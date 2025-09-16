package lesson10.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

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

  @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
  public byte[] takeScreenshot() {
    return ((TakesScreenshot)WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
  }
}
