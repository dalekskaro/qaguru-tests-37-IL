package lesson10.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;

public class OutputComponent {
    SelenideElement nameString = $("#output #name"),
            emailString = $("#output #email"),
            currentAddressString = $("#output #currentAddress"),
            permanentAddressString = $("#output #permanentAddress");

    public void checkValueInNameString(String value) {
        nameString.shouldHave(text(value));
    }

    public void checkValueInEmailString(String value) {
        emailString.shouldHave(text(value));
    }

    public void checkValueInCurrentAddressString(String value) {
        currentAddressString.shouldHave(text(value));
    }

    public void checkValueInPermanentAddressString(String value) {
        permanentAddressString.shouldHave(text(value));
    }
}
