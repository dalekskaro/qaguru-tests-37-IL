package lesson6_7_11l9.pages;

import com.codeborne.selenide.SelenideElement;
import lesson6_7_11l9.pages.components.OutputComponent;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {

  OutputComponent outputComponent = new OutputComponent();

  private final SelenideElement fullNameInput = $("#userName"),
      emailInput = $("#userEmail"),
      currentAddressInput = $("#currentAddress"),
      permanentAddressInput = $("#permanentAddress"),
      submitButton = $("#submit");

  public TextBoxPage openPage() {
    open("/text-box");

    return this;
  }

  public TextBoxPage removeAdd() {
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");

    return this;
  }

  public TextBoxPage setFullName(String value) {
    fullNameInput.setValue(value);

    return this;
  }

  public TextBoxPage setEmail(String value) {
    emailInput.setValue(value);

    return this;
  }

  public TextBoxPage setCurrentAddress(String value) {
    currentAddressInput.setValue(value);

    return this;
  }

  public TextBoxPage setPermanentAddress(String value) {
    permanentAddressInput.setValue(value);

    return this;
  }

  public TextBoxPage clickOnSubmitButton() {
    submitButton.click();

    return this;
  }

  public TextBoxPage checkOutputValueName(String value) {
    outputComponent.checkValueInNameString(value);

    return this;
  }

  public TextBoxPage checkOutputValueEmail(String value) {
    outputComponent.checkValueInEmailString(value);

    return this;
  }

  public TextBoxPage checkOutputValueCurrentAddress(String value) {
    outputComponent.checkValueInCurrentAddressString(value);

    return this;
  }

  public TextBoxPage checkOutputValuePermananetAddress(String value) {
    outputComponent.checkValueInPermanentAddressString(value);

    return this;
  }
}
