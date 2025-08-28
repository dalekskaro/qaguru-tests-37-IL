package lesson6_7.pages;

import com.codeborne.selenide.SelenideElement;
import lesson6_7.pages.components.CalendarComponent;
import lesson6_7.pages.components.ModalTableComponent;
import lesson6_7.utils.DateUtils;
import lesson6_7.utils.DayOfMonthAndYear;
import lesson6_7.utils.FileCreator;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class StudentRegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    ModalTableComponent modalTableComponent = new ModalTableComponent();
    DateUtils dateUtils = new DateUtils();

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

    public StudentRegistrationPage openPage() {
        open("/automation-practice-form");

        return this;
    }

    public StudentRegistrationPage removeAdd() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        return this;
    }

    public StudentRegistrationPage setFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public StudentRegistrationPage setLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public StudentRegistrationPage setEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public StudentRegistrationPage selectGender(String value) {
        genderWrapper.$(byText(value)).click();

        return this;
    }

    public StudentRegistrationPage setMobileNumber(String value) {
        mobileNumberInput.setValue(value);

        return this;
    }

    public StudentRegistrationPage setDateOfBirth(Date date) {
        DayOfMonthAndYear dayOfMonthAndYear = dateUtils.dateTranslation(date);

        dateOfBirthInput.click();
        calendarComponent.setDate(dayOfMonthAndYear.getDay(), dayOfMonthAndYear.getMonth(), dayOfMonthAndYear.getYear());

        return this;
    }

    public StudentRegistrationPage setSubjects(String value) {
        subjectsInput.setValue(value).pressEnter();

        return this;
    }

    public StudentRegistrationPage selectHobbies(String value) {
        hobbiesWrapper.$(byText(value)).click();

        return this;
    }

    public StudentRegistrationPage uploadPicture(String fileName) throws IOException {
        File file = FileCreator.pngCreator(fileName);
        uploadPictureButton.uploadFile(file);

        return this;
    }

    public StudentRegistrationPage setAddress(String value) {
        currentAddressInput.setValue(value);

        return this;
    }

    public StudentRegistrationPage selectState(String value) {
        stateDropdownList.click();
        $(byText(value)).click();

        return this;
    }

    public StudentRegistrationPage selectCity(String value) {
        cityDropdownList.click();
        $(byText(value)).click();

        return this;
    }

    public StudentRegistrationPage clickSubmitButton() {
        submitButton.scrollTo().click();

        return this;
    }

    public StudentRegistrationPage checkResultInSubmittingModalLabel(String value) {
        modalTableComponent.checkResultInSubmittingModalLabel(value);

        return this;
    }

    public StudentRegistrationPage checkResultInSubmittingModalTableThead(String value) {
        modalTableComponent.checkResultInSubmittingModalTableThead(value);

        return this;
    }

    public StudentRegistrationPage checkResultInSubmittingModalTable(String label, String value) {
        modalTableComponent.checkDataInSubmittingModalTable(label, value);

        return this;
    }

    public StudentRegistrationPage checkResultDateInSubmittingModalTable(String label, Date date) {
            DayOfMonthAndYear dayOfMonthAndYear = dateUtils.dateTranslation(date);

        modalTableComponent.checkDataDateInSubmittingModalTable(label,
                dayOfMonthAndYear.getDay() + " " + dayOfMonthAndYear.getMonth() + "," + dayOfMonthAndYear.getYear());

        return this;
    }

    public StudentRegistrationPage checkColorOfFirstNameInput(String value) {
        firstNameInput.getCssValue("border-color").equals(value);

        return this;
    }

    public StudentRegistrationPage checkColorOfLastNameInput(String value) {
        lastNameInput.getCssValue("border-color").equals(value);

        return this;
    }

    public StudentRegistrationPage checkColorOfGenderOtherRadio(String value) {
        genderOtherRadio.getCssValue("border-color").equals(value);

        return this;
    }

    public StudentRegistrationPage checkColorOfGenderMaleRadio(String value) {
        genderMaleRadio.getCssValue("border-color").equals(value);

        return this;
    }

    public StudentRegistrationPage checkColorOfGenderFemaleRadio(String value) {
        genderFemaleRadio.getCssValue("border-color").equals(value);

        return this;
    }

    public StudentRegistrationPage checkColorOfMobileNumberInput(String value) {
        mobileNumberInput.getCssValue("border-color").equals(value);

        return this;
    }
}
