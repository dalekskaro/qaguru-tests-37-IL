package lesson6_7.tests;

import lesson6_7.pages.StudentRegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static lesson6_7.utils.CreateRandomData.*;

public class StudentRegistrationFormTest extends BaseTest {

    StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();

    String firstName = getFirstName(),
            lastName = getLastName(),
            email = getEmail(),
            gender = getGender(),
            mobileNumber = getMobileNumber(),
            day = getDayAsString(),
            month = getMonthAsString(),
            year = getYearAsString(),
            subject = getSubject(),
            hobbies = getHobbies(),
            picture = getPicture(),
            address = getAddress(),
            state = getState(),
            city = getCity(state);

    public StudentRegistrationFormTest() throws IOException {
    }

    @Test
    @DisplayName("Проверка заполнения всех полей на форме регистрации студента")
    void fillAllFieldsOnStudentRegistrationFormPositiveTest() throws IOException {
        studentRegistrationPage.openPage()
                .removeAdd()
                .setFirstName(firstName)
                .setLastName(lastName)
                .setEmail(email)
                .selectGender(gender)
                .setMobileNumber(mobileNumber)
                .setDateOfBirth(day, month, year)
                .setSubjects(subject)
                .selectHobbies(hobbies)
                .uploadPicture(picture)
                .setAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmitButton();

        studentRegistrationPage.checkResultInSubmittingModalLabel("Thanks for submitting the form")
                .checkResultInSubmittingModalTableThead("Label")
                .checkResultInSubmittingModalTableThead("Values")
                .checkResultInSubmittingModalTable("Student Name", firstName + " " + lastName)
                .checkResultInSubmittingModalTable("Student Email", email)
                .checkResultInSubmittingModalTable("Gender", gender)
                .checkResultInSubmittingModalTable("Mobile", mobileNumber)
                .checkResultInSubmittingModalTable("Date of Birth", day + " " + month + "," + year)
                .checkResultInSubmittingModalTable("Subjects", subject)
                .checkResultInSubmittingModalTable("Hobbies", hobbies)
                .checkResultInSubmittingModalTable("Picture", picture)
                .checkResultInSubmittingModalTable("Address", address)
                .checkResultInSubmittingModalTable("State and City", state + " " + city);
    }

    @Test
    @DisplayName("Проверка заполнения только обязательных полей на форме регистрации студента")
    void fillOnlyRequiredFieldsOnStudentRegistrationFormPositiveTest() {
        studentRegistrationPage.openPage()
                .removeAdd()
                .setFirstName(firstName)
                .setLastName(lastName)
                .selectGender(gender)
                .setMobileNumber(mobileNumber)
                .clickSubmitButton();

        studentRegistrationPage.checkResultInSubmittingModalLabel("Thanks for submitting the form")
                .checkResultInSubmittingModalTableThead("Label")
                .checkResultInSubmittingModalTableThead("Values")
                .checkResultInSubmittingModalTable("Student Name", firstName + " " + lastName)
                .checkResultInSubmittingModalTable("Gender", gender)
                .checkResultInSubmittingModalTable("Mobile", mobileNumber);
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
