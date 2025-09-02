package lesson8.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class SwagLabsProductPage {
    private final SelenideElement
            header = $(".header_secondary_container");

    public SwagLabsProductPage checkOnPage() {
        header.shouldHave(text("Products"));

        return this;
    }
}
