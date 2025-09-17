package lesson10.tests;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import io.qameta.allure.Description;
import lesson10.pages.RepositoryPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("homework-10")
public class HomeworkIssueGitHubTests extends HomeworkAllureBaseTest {

  private static final String
      URL = "dalekskaro/qaguru-tests-37-IL",
      TEXT = "Issues";

  @Test
  @DisplayName("Проверка, что есть наименование Issue в репозитории")
  @Description("1. Чистый Selenide (с Listener)")
  public void selenideHwTest() {
    open(URL);
    $("#issues-tab").shouldHave(text("Issues"));
  }

  @Test
  @DisplayName("Проверка, что есть наименование Issue в репозитории")
  @Description("2. Лямбда шаги через step (name, () -> {})")
  public void lambdaHwTest() {
    step("Открываем страницу " + URL, () -> {
      open(URL);
    });

    step("Проверяем, что в репозитории есть название Issues", () -> {
      $("#issues-tab").shouldHave(text(TEXT));
    });
  }

  @Test
  @DisplayName("Проверка, что есть наименование Issue в репозитории")
  @Description("3. Шаги с аннотацией @Step")
  public void annotationHwTest() {
    RepositoryPage repositoryPage = new RepositoryPage();

    repositoryPage
        .openPage(URL)
        .checkTextOnIssuesTab(TEXT);
  }
}
