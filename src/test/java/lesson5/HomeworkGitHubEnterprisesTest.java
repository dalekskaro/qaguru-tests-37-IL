package lesson5;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeworkGitHubEnterprisesTest {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void solutionsEnterprisesTest() {
        // На главной странице GitHub выберите: Меню -> Solutions -> Enterprises (с помощью команды hover для Solutions).
        open("");
        $(".HeaderMenu").$(byText("Solutions")).hover();
        $(byText("Enterprises")).click();

        // Убедитесь, что загрузилась нужная страница (например, что заголовок: "The AI-powered developer platform.").
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered developer platform"));
        $("#hero-section-brand-heading").sibling(0).shouldHave(text("To build, scale, and deliver secure software."));
        $(".Primer_Brand__Button-module__Button__text___Z3ocU").shouldHave(text("Start free for 30 days"));
    }
}
