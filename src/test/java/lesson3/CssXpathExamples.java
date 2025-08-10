package lesson3;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CssXpathExamples {
    void cssXpath() {
        // <input type="email" class="inputText login_form_input_box" name="email" id="email" data-testid="email">
        $("[data-testid=email]");
        $(by("data-testid", "email"));
        $x("//*[data-testid='email']");

        // <input type="email" class="inputText login_form_input_box" name="email" id="email">
        $("#email"); // лучший вариант
        $("[id=email]");
        $(by("id", "email"));
        $(byId("email"));
        $x("//*[id='email']");

        // <input type="email" class="inputText login_form_input_box" name="email">
        $("[name=email]");
        $(by("name", "email"));
        $(byName("email"));
        $x("//*[name='email']");

        // <input type="email" class="inputText login_form_input_box">
        $("[class=inputText] [class=login_form_input_box]");
        $(".inputText.login_form_input_box");
        $(".login_form_input_box"); // лучший вариант
        $("input.inputText.login_form_input_box");
        $x("//input[@class='inputText'][@class='login_form_input_box']");

        // <div class="inputText">
        //      <input type="email" class="inputText login_form_input_box">
        // </div>
        $(".inputText .login_form_input_box");
        $(".inputText").$(".login_form_input_box");
        $("div.inputText").$(".login_form_input_box");
        $("div.inputText .login_form_input_box");

        // <div>Hello, Barovia</div>
        $(byText("Hello, Barovia"));
        $(withText("llo, Bar"));
        $x("//*[text()='Hello, Barovia']"); // моветон в xpath
        $x("//*[contains(text(), 'Hello, Barovia')]"); // в xpath лучше писать так
    }
}
