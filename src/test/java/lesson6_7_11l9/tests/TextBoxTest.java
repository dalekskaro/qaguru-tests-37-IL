package lesson6_7_11l9.tests;

import lesson6_7_11l9.pages.TextBoxPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TextBoxTest extends BaseTest {

  TextBoxPage textBoxPage = new TextBoxPage();

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
