package lesson4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HomeworkSelenideWikiTest {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://github.com/";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void selenideWikiTest() {
        //- Откройте страницу Selenide в Github
        open("/selenide/selenide");

        //- Перейдите в раздел Wiki проекта
        $("#wiki-tab").click();

        //- Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions
        $("#wiki-pages-box").$(byText("Show 3 more pages…")).click();
        $("#wiki-pages-box").shouldHave(text("SoftAssertions"));

        //- Откройте страницу SoftAssertions
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();

        // Проверьте что внутри есть пример кода для JUnit5
        $(byText("3. Using JUnit5 extend test class:")).parent()
                .sibling(0).shouldHave(text("@ExtendWith({SoftAssertsExtension.class})"));
    }
}
