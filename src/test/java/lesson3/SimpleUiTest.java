package lesson3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SimpleUiTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager"; // Ускорение загрузки страниц
        // Configuration.timeout = 5000; // Если нужен таймаут, default = 4000
    }

    @Test
    void fillFormTest() {
        open("/text-box");

        $("#userName").setValue("Strahd von Zarovich");
        $("#userEmail").setValue("strahd@barovia.dom");
        $("#currentAddress").setValue("Ravenloft, Barovia");
        $("#permanentAddress").setValue("Amber Temple, Barovia");
        $("#submit").click();

        $("#output #name").shouldHave(text("Strahd von Zarovich"));
        $("#output #email").shouldHave(text("strahd@barovia.dom"));
        $("#output #currentAddress").shouldHave(text("Ravenloft, Barovia"));
        $("#output #permanentAddress").shouldHave(text("Amber Temple, Barovia"));
    }
}
