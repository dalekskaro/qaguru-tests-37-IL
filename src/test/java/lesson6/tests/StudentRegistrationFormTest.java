package lesson6.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StudentRegistrationFormTest extends BaseTest {

    @Test
    @DisplayName("Проверка заполнения всех полей на форме регистрации студента")
    void fillAllFieldsOnStudentRegistrationFormPositiveTest() {
        studentRegistrationPage.openPage()
                .removeAdd()
                .setFirstName("Strahd")
                .setLastName("von Zarovich")
                .setEmail("strahd@barovia.dom")
                .selectMaleGender()
                .setMobileNumber("8005553535")
                .setDateOfBirth("01", "January", "2000")
                .setSubjects("Maths")
                .selectHobbies("Reading")
                .uploadPicture("lesson3/knight.jpg")
                .setAddress("Barovia, Ravenloft Castle, Area K86 \\\"Strahd's Tomb\\\"")
                .selectState("NCR")
                .selectCity("Delhi")
                .clickSubmitButton();

        studentRegistrationPage.checkResultInSubmittingModalLabel("Thanks for submitting the form")
                .checkResultInSubmittingModalTableThead("Label")
                .checkResultInSubmittingModalTableThead("Values")
                .checkResultInSubmittingModalTable("Student Name", "Strahd von Zarovich")
                .checkResultInSubmittingModalTable("Student Email", "strahd@barovia.dom")
                .checkResultInSubmittingModalTable("Gender", "Male")
                .checkResultInSubmittingModalTable("Mobile", "8005553535")
                .checkResultInSubmittingModalTable("Date of Birth", "01 January,2000")
                .checkResultInSubmittingModalTable("Subjects", "Maths")
                .checkResultInSubmittingModalTable("Hobbies", "Reading")
                .checkResultInSubmittingModalTable("Picture", "knight.jpg")
                .checkResultInSubmittingModalTable("Address", "Barovia, Ravenloft Castle, Area K86 \\\"Strahd's Tomb\\\"")
                .checkResultInSubmittingModalTable("State and City", "NCR Delhi");
    }

    @Test
    @DisplayName("Проверка заполнения только обязательных полей на форме регистрации студента")
    void fillOnlyRequiredFieldsOnStudentRegistrationFormPositiveTest() {
        studentRegistrationPage.openPage()
                .removeAdd()
                .setFirstName("Hello")
                .setLastName("Kitty")
                .selectFemaleGender()
                .setMobileNumber("8005553535")
                .clickSubmitButton();

        studentRegistrationPage.checkResultInSubmittingModalLabel("Thanks for submitting the form")
                .checkResultInSubmittingModalTableThead("Label")
                .checkResultInSubmittingModalTableThead("Values")
                .checkResultInSubmittingModalTable("Student Name", "Hello Kitty")
                .checkResultInSubmittingModalTable("Gender", "Female")
                .checkResultInSubmittingModalTable("Mobile", "8005553535");
    }

    @Test
    @DisplayName("Проверка подсвечивания обязательный полей, если они не заполнены")
    void noFillStudentRegistrationFormNegativeTest() {
        studentRegistrationPage.openPage()
                .clickSubmitButton();

        studentRegistrationPage
                .checkColorOfFirstNameInput("rgb(220, 53, 69)")
                .checkColorOfLastNameInput("rgb(220, 53, 69)")
                .checkColorOfGenderMaleRadio("rgb(220, 53, 69)")
                .checkColorOfGenderFemaleRadio("rgb(220, 53, 69)")
                .checkColorOfGenderOtherRadio("rgb(220, 53, 69)")
                .checkColorOfMobileNumberInput("rgb(220, 53, 69)");
    }
}
