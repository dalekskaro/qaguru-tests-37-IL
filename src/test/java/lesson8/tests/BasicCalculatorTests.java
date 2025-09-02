package lesson8.tests;

import lesson8.pages.BasicCalculatorPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

@Tag("homework-8")
public class BasicCalculatorTests extends BaseTestForCalculator {
    BasicCalculatorPage basicCalculatorPage = new BasicCalculatorPage();

    @CsvSource(value = {
            "12, 53, Add, 65",
            "34, 54, Subtract, -20",
            "12, 45, Multiply, 540",
            "68, 8, Divide, 8.5",
            "hell, o, Concatenate, hello"
    })
    @ParameterizedTest(name = "Вычисление операции {2}: {0} {2} {1} = {3}")
    @DisplayName("Аннотация @CsvSource")
    void calculateTestWithScvSource(String firstNumber, String secondNumber, String operation, String answer) {
        basicCalculatorPage.openPage()
                .scrollToCalculator()
                .setFirstNumber(firstNumber)
                .setSecondNumber(secondNumber)
                .selectOperation(operation)
                .clickOnCalculateButton()
                .checkAnswer(answer);
    }

    @CsvFileSource(resources = "/lesson8/Calculator.csv", numLinesToSkip = 1)
    @ParameterizedTest(name = "Вычисление операции {2}: {0} {2} {1} = {3}")
    @DisplayName("Аннотация @CsvFileSource")
    void calculateTestWithCsvFileSource(String firstNumber, String secondNumber, String operation, String answer) {
        basicCalculatorPage.openPage()
                .scrollToCalculator()
                .setFirstNumber(firstNumber)
                .setSecondNumber(secondNumber)
                .selectOperation(operation)
                .clickOnCalculateButton()
                .checkAnswer(answer);
    }
}
