package lesson10.pages;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

public class RepositoryPage {
//  open(URL);
//  $("#issues-tab").shouldHave(text("Issues"));
private final SelenideElement issuesTab = $("#issues-tab");

  @Step("Открываем страницу {url}")
  public RepositoryPage openPage(String url) {
    open(url);
    return this;
  }

  @Step("Проверка, что в репозитории есть название Issues")
  public RepositoryPage checkTextOnIssuesTab(String text) {
    issuesTab.shouldHave(text(text));
    return this;
  }
}
