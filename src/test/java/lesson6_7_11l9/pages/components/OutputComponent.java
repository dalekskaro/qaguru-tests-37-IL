package lesson6_7_11l9.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class OutputComponent {

  SelenideElement
      nameString = $("#output #name"),
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
