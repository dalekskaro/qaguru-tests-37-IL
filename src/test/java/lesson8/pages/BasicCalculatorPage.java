package lesson8.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class BasicCalculatorPage {

    private final SelenideElement
            calculatorForm = $("#calcForm"),
            firstNumberField = $("#number1Field"),
            secondNumberField = $("#number2Field"),
            operationDropdown = $("#selectOperationDropdown"),
            calculateButton = $("#calculateButton"),
            numberAnswerField = $("#numberAnswerField");

    public BasicCalculatorPage openPage() {
        open("/BasicCalculator.html");

        return this;
    }

    public BasicCalculatorPage scrollToCalculator() {
        calculatorForm.scrollTo();

        return this;
    }

    public BasicCalculatorPage setFirstNumber(String firstNumber) {
        firstNumberField.setValue(firstNumber);

        return this;
    }

    public BasicCalculatorPage setSecondNumber(String secondNumber) {
        secondNumberField.setValue(secondNumber);

        return this;
    }

    public BasicCalculatorPage selectOperation(String operation) {
        operationDropdown.click();
        operationDropdown.$(byText(operation)).click();

        return this;
    }

    public BasicCalculatorPage clickOnCalculateButton() {
        calculateButton.click();

        return this;
    }

    public BasicCalculatorPage checkAnswer(String answer) {
            numberAnswerField.shouldHave(attribute("value",answer));
        return this;
    }
}
