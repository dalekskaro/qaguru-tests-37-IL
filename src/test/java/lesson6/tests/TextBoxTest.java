package lesson6.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TextBoxTest extends BaseTest {

    @Test
    @DisplayName("Проверка отправки формы на Text Box")
    void fillTextBoxPositiveTest() {
        textBoxPage.openPage()
                .removeAdd()
                .setFullName("Sirius Black")
                .setEmail("atyd@hogwarts.owl")
                .setCurrentAddress("Gryffindor")
                .setPermanentAddress("Azkaban")
                .clickOnSubmitButton();

        textBoxPage.checkOutputValueName("Sirius Black")
                .checkOutputValueEmail("atyd@hogwarts.owl")
                .checkOutputValueCurrentAddress("Gryffindor")
                .checkOutputValuePermananetAddress("Azkaban");
    }
}
