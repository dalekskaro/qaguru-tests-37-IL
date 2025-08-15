package lesson4;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class hDivTest {
    //    $("h1 div");
//        $("h1").$("div");
    @Test
    void letsSeeDivTest() {
// body div
//        open("/frames");
        $("body div").should(appear);
        $("body").$("div").should(appear);
    }

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void letsSeeDiTest() {
// body div
        open("https://demoqa.com/frames");
        System.out.println("    ### open");
        $("div h1").should(appear); // Element not found {div h12}
        System.out.println("    ### assert 1");
        $("div").$("h1").should(appear); //Element not found {div/h1}
        System.out.println("    ### assert 1");
    }

    @Test
    void letsSeeDTest() {
// body div
        open("https://the-internet.herokuapp.com/nested_frames");
        $("html head").should(appear);
        $("html").$("head").should(appear);
    }
}
