package lesson6_7_11l9.tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Link;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import lesson6_7_11l9.pages.StudentRegistrationPage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static lesson6_7_11l9.utils.CreateRandomData.*;

@Feature("Форма 'Student Registration Form'")
public class StudentRegistrationFormTest extends BaseTest {

  StudentRegistrationPage studentRegistrationPage = new StudentRegistrationPage();

  String firstName = getFirstName(),
      lastName = getLastName(),
      email = getEmail(),
      gender = getGender(),
      mobileNumber = getMobileNumber(),
      birthDay = getBirthDayAsString(),
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

  @Tag("homework-11")
  @Test
  @Story("Заполнение формы")
  @Owner("Irina Attano")
  @Severity(SeverityLevel.BLOCKER)
  @Link(value = "Testing url", url = "https://demoqa.com/automation-practice-form")
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

  @Tag("homework-11")
  @Test
  @Story("Заполнение формы")
  @Owner("Irina Attano")
  @Severity(SeverityLevel.BLOCKER)
  @Link(value = "Testing url", url = "https://demoqa.com/automation-practice-form")
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

  @Tag("homework-11")
  @Test
  @Story("Подсветка обязательных полей")
  @Owner("Irina Attano")
  @Severity(SeverityLevel.NORMAL)
  @Link(value = "Testing url", url = "https://demoqa.com/automation-practice-form")
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

  @Test
  @Story("Проверка альтернативного метода генерации даты")
  @Owner("Irina Attano")
  @Severity(SeverityLevel.MINOR)
  @Link(value = "Testing url", url = "https://demoqa.com/automation-practice-form")
  @DisplayName("Проверка работоспособности другого метода генерации даты рождения")
  void fillRequiredFieldsAndBdayOnStudentRegistrationFormPositiveTest() {
    studentRegistrationPage.openPage()
        .removeAdd()
        .setFirstName(firstName)
        .setLastName(lastName)
        .selectGender(gender)
        .setMobileNumber(mobileNumber)
        .setDateOfBirthWhenDayIsOneValue(birthDay)
        .clickSubmitButton();

    studentRegistrationPage.checkResultInSubmittingModalLabel("Thanks for submitting the form")
        .checkResultInSubmittingModalTableThead("Label")
        .checkResultInSubmittingModalTableThead("Values")
        .checkResultInSubmittingModalTable("Student Name", firstName + " " + lastName)
        .checkResultInSubmittingModalTable("Gender", gender)
        .checkResultInSubmittingModalTable("Mobile", mobileNumber)
        .checkResultWithDateInSubmittingModalTable("Date of Birth", birthDay);
  }
}
