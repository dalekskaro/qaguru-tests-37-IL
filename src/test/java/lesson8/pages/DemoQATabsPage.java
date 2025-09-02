package lesson8.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class DemoQATabsPage {
    private final ElementsCollection
    tab = $$("[role='tab']");

    private final SelenideElement
    text = $(".tab-content");

    public DemoQATabsPage openPage() {
        open("/tabs");

        return this;
    }

    public DemoQATabsPage clickOnTab(String tabName) {
        tab.find(text(tabName)).click();

        return this;
    }

    public DemoQATabsPage checkTextOnTab(String textOnTab) {
        text.shouldHave(text(textOnTab));

        return this;
    }
}
