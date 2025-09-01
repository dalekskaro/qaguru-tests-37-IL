package lesson8.tests;

import com.codeborne.selenide.Configuration;
import lesson8.data.Language;
import lesson8.data.Person;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWebTest {
    @BeforeAll
    static void setUpBrowser() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        open("https://ru.selenide.org/");
    }

    @Tag("allSelenide") @Tag("onlyThis")
    @EnumSource(Language.class)
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectText(Language language) {
        $$("#languages a").find(text(language.name())).click();
        $("h3").shouldHave(text(language.description));
    }

    static Stream<Arguments> selenideSiteShouldDisplayCorrectButtons() {
        return Stream.of(
                Arguments.of(
                        Language.EN,
                        List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"),
                        new Person("Dima", 34)
                ),
                Arguments.of(
                        Language.RU,
                        List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"),
                        new Person("Valentin", 35)
                )
        );
    }

    @Tag("allSelenide")
    @MethodSource
    @ParameterizedTest
    void selenideSiteShouldDisplayCorrectButtons(Language language, List<String> expectedButtons, Person person) {
        $$("#languages a").find(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(expectedButtons));
    }
}
