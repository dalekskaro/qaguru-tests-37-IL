package lesson6_7_11l9.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import lesson6_7_11l9.pages.components.CalendarComponent;
import lesson6_7_11l9.pages.components.ModalTableComponent;
import lesson6_7_11l9.utils.FileCreator;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationPage {

  CalendarComponent calendarComponent = new CalendarComponent();
  ModalTableComponent modalTableComponent = new ModalTableComponent();

  private final SelenideElement firstNameInput = $("#firstName"),
      lastNameInput = $("#lastName"),
      emailInput = $("#userEmail"),
      genderWrapper = $("#genterWrapper"),
      genderMaleRadio = $("#genterWrapper").$(byText("Male")),
      genderFemaleRadio = $("#genterWrapper").$(byText("Female")),
      genderOtherRadio = $("#genterWrapper").$(byText("Other")),
      mobileNumberInput = $("#userNumber"),
      dateOfBirthInput = $("#dateOfBirthInput"),
      subjectsInput = $("#subjectsInput"),
      hobbiesWrapper = $("#hobbiesWrapper"),
      uploadPictureButton = $("#uploadPicture"),
      currentAddressInput = $("#currentAddress"),
      stateDropdownList = $("#state"),
      cityDropdownList = $("#city"),
      submitButton = $("#submit");

  @Step("Открываем страницу /automation-practice-form")
  public StudentRegistrationPage openPage() {
    open("/automation-practice-form");

    return this;
  }

  @Step("Убираем рекламу")
  public StudentRegistrationPage removeAdd() {
    executeJavaScript("$('#fixedban').remove()");
    executeJavaScript("$('footer').remove()");

    return this;
  }

  @Step("В поле 'First Name' вводим значение {firstName}")
  public StudentRegistrationPage setFirstName(String firstName) {
    firstNameInput.setValue(firstName);

    return this;
  }

  @Step("В поле 'Last Name' вводим значение '{lastName}'")
  public StudentRegistrationPage setLastName(String lastName) {
    lastNameInput.setValue(lastName);

    return this;
  }

  @Step("В поле 'Email' вводим значение '{email}'")
  public StudentRegistrationPage setEmail(String email) {
    emailInput.setValue(email);

    return this;
  }

  @Step("Выбираем gender '{gender}'")
  public StudentRegistrationPage selectGender(String gender) {
    genderWrapper.$(byText(gender)).click();

    return this;
  }

  @Step("В поле 'Mobile' вводим значение '{mobile}'")
  public StudentRegistrationPage setMobileNumber(String mobile) {
    mobileNumberInput.setValue(mobile);

    return this;
  }

  @Step("В календаре выбираем дату рождения: день {day}, месяц {month}, год {year}")
  public StudentRegistrationPage setDateOfBirth(String day, String month, String year) {
    dateOfBirthInput.click();
    calendarComponent.setDate(day, month, year);

    return this;
  }

  @Step("В календаре выбираем дату рождения (одно значение): {birthDay}")
  public StudentRegistrationPage setDateOfBirthWhenDayIsOneValue(String birthDay) {
    dateOfBirthInput.click();
    calendarComponent.setDateWhenOneValue(birthDay);

    return this;
  }

  @Step("В поле 'Subjects' выбираем значение '{subjects}'")
  public StudentRegistrationPage setSubjects(String subjects) {
    subjectsInput.setValue(subjects).pressEnter();

    return this;
  }

  @Step("Выбираем hobbies: '{hobbies}'")
  public StudentRegistrationPage selectHobbies(String hobbies) {
    hobbiesWrapper.$(byText(hobbies)).click();

    return this;
  }

  @Step("Загружаем картинку: '{fileName}'")
  public StudentRegistrationPage uploadPicture(String fileName) throws IOException {
    File file = FileCreator.pngCreator(fileName);
    uploadPictureButton.uploadFile(file);

    return this;
  }

  @Step("В поле 'Current Address' вводим значение '{currentAddress}'")
  public StudentRegistrationPage setAddress(String currentAddress) {
    currentAddressInput.setValue(currentAddress);

    return this;
  }

  @Step("В выпадающем списке 'Select State' выбираем значение '{state}'")
  public StudentRegistrationPage selectState(String state) {
    stateDropdownList.click();
    $(byText(state)).click();

    return this;
  }

  @Step("В выпадающем списке 'Select City' выбираем значение '{city}'")
  public StudentRegistrationPage selectCity(String city) {
    cityDropdownList.click();
    $(byText(city)).click();

    return this;
  }

  @Step("Нажимаем на кнопку 'Submit'")
  public StudentRegistrationPage clickSubmitButton() {
    submitButton.scrollTo().click();

    return this;
  }

  @Step("Проверяем, что на в хедере модального окна есть текст '{text}'")
  public StudentRegistrationPage checkResultInSubmittingModalLabel(String text) {
    modalTableComponent.checkResultInSubmittingModalLabel(text);

    return this;
  }

  @Step("Проверяем, что в модальном окне есть столбец с заголовком '{value}'")
  public StudentRegistrationPage checkResultInSubmittingModalTableThead(String value) {
    modalTableComponent.checkResultInSubmittingModalTableThead(value);

    return this;
  }

  @Step("Проверяем, что в модальном окне в строке '{label}' записано значение '{value}'")
  public StudentRegistrationPage checkResultInSubmittingModalTable(String label, String value) {
    modalTableComponent.checkDataInSubmittingModalTable(label, value);

    return this;
  }

  @Step("Проверяем, что в модальном окне в строке '{label}' записано значение с датой '{value}'")
  public StudentRegistrationPage checkResultWithDateInSubmittingModalTable(String label, String value) {
    modalTableComponent.checkDateInSubmittingModalTable(label, value);

    return this;
  }

  @Step("Проверяем, что поле 'First Name' подсвечено цветом '{value}'")
  public StudentRegistrationPage checkColorOfFirstNameInput(String value) {
    firstNameInput.getCssValue("border-color").equals(value);

    return this;
  }

  @Step("Проверяем, что поле 'Last Name' подсвечено цветом '{value}'")
  public StudentRegistrationPage checkColorOfLastNameInput(String value) {
    lastNameInput.getCssValue("border-color").equals(value);

    return this;
  }

  @Step("Проверяем, что радио-кнопка 'Gender Other' подсвечена цветом '{value}'")
  public StudentRegistrationPage checkColorOfGenderOtherRadio(String value) {
    genderOtherRadio.getCssValue("border-color").equals(value);

    return this;
  }

  @Step("Проверяем, что радио-кнопка 'Gender Male' подсвечена цветом '{value}'")
  public StudentRegistrationPage checkColorOfGenderMaleRadio(String value) {
    genderMaleRadio.getCssValue("border-color").equals(value);

    return this;
  }

  @Step("Проверяем, что радио-кнопка 'Gender Female' подсвечена цветом '{value}'")
  public StudentRegistrationPage checkColorOfGenderFemaleRadio(String value) {
    genderFemaleRadio.getCssValue("border-color").equals(value);

    return this;
  }

  @Step("Проверяем, что поле 'Mobile' подсвечено цветом '{value}'")
  public StudentRegistrationPage checkColorOfMobileNumberInput(String value) {
    mobileNumberInput.getCssValue("border-color").equals(value);

    return this;
  }
}
