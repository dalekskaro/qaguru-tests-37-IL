package lesson8.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SwagLabsAuthPage {
    private final SelenideElement
    loginField = $("#user-name"),
    passwordField = $("#password"),
    loginButton = $("#login-button");

    public SwagLabsAuthPage openAuthPage() {
        open("");

        return this;
    }

    public SwagLabsAuthPage setLogin(String login) {
        loginField.setValue(login);

        return this;
    }

    public SwagLabsAuthPage setHardcodePassword() {
        passwordField.setValue("secret_sauce");

        return this;
    }

    public SwagLabsAuthPage clickOnLoginButton() {
        loginButton.click();

        return this;
    }
}
