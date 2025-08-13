package lesson4;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class LessonsTests {
    @Test
    void shouldFindSelenideRepositoryAtTheTop() {
        // Открыть главную страницу
        open("https://github.com/");
        // Ввести в поле поиска selenide и нажать enter
//        $("[placeholder='Search GitHub']").setValue("selenide").pressEnter(); - сейчас такой код уже не работает
        $(".search-input").click();
        $("#query-builder-test").setValue("selenide").pressEnter();
        // Кликнуть на первый репозиторий из списка найденных
//        $$("ul.repo-list li").first().$("a").click();  - сейчас такой код уже не работает
        $$("[data-testid=\"results-list\"]").first().$("a").click();
        // проверка: заголовок selenide/selenide
        $("#repository-container-header").shouldHave(text("selenide / selenide"));
    }

    @Test
    void andreiSolntsevShouldBeTheFirstContributor() {
        // Открыть страницу репозитория селенида
        open("https://github.com/selenide/selenide");
        // Подвести мышку к первому аватару из блока contributors
        $("div.Layout-sidebar").$(byText("Contributors"))
                //.closest(".BorderGrid-cell").$$("ul li").first().hover();
                .closest("h2").sibling(0).$$("li").first().hover();
        // Проверка: во всплывающем окне есть текст Andrei Solntsev
        // Тайминг 1:10:00 - как поймать всплывающее окно
        $(".Popover-message").shouldHave(text("Andrei Solntsev"));
    }
}
