package lesson10.pages;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;
import static com.codeborne.selenide.Selenide.open;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lesson10.pages.components.OutputComponent;

public class TextBoxPage {
    OutputComponent outputComponent = new OutputComponent();

    private final SelenideElement fullNameInput = $("#userName"),
            emailInput = $("#userEmail"),
            currentAddressInput = $("#currentAddress"),
            permanentAddressInput = $("#permanentAddress"),
            submitButton = $("#submit");

    @Step("")
    public TextBoxPage openPage() {
        open("/text-box");

        return this;
    }

    @Step("")
    public TextBoxPage removeAdd() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    @Step("")
    public TextBoxPage setFullName(String value) {
        fullNameInput.setValue(value);

        return this;
    }

    @Step("")
    public TextBoxPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    @Step("")
    public TextBoxPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    @Step("")
    public TextBoxPage setPermanentAddress(String value) {
        permanentAddressInput.setValue(value);

        return this;
    }

    @Step("")
    public TextBoxPage clickOnSubmitButton() {
        submitButton.click();

        return this;
    }

    @Step("")
    public TextBoxPage checkOutputValueName(String value) {
        outputComponent.checkValueInNameString(value);

        return this;
    }

    @Step("")
    public TextBoxPage checkOutputValueEmail(String value) {
        outputComponent.checkValueInEmailString(value);

        return this;
    }

    @Step("")
    public TextBoxPage checkOutputValueCurrentAddress(String value) {
        outputComponent.checkValueInCurrentAddressString(value);

        return this;
    }

    @Step("")
    public TextBoxPage checkOutputValuePermananetAddress(String value) {
        outputComponent.checkValueInPermanentAddressString(value);

        return this;
    }
}
