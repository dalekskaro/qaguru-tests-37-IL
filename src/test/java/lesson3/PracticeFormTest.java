package lesson3;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTest {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFullFormTest() {
        open("/automation-practice-form");
        // Fill 'Name'
        $("#firstName").setValue("Strahd");
        $("#lastName").setValue("von Zarovich");
        // Fill 'Email'
        $("#userEmail").setValue("strahd@barovia.dom");
        // Fill 'Gender'
        $("#genterWrapper").$(byText("Male")).click();
        // Fill 'Mobile(10 Digits)'
        $("#userNumber").setValue("8005553535");
        // Fill 'Date of Birth'
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").$(byText("2000")).click();
        $(".react-datepicker__month-select").$(byText("January")).click();
        $(".react-datepicker__day--001").click();
        // Fill 'Subjects'
        $("#subjectsInput").setValue("Math");
        $(byText("Maths")).click();
        // Fill 'Hobbies'
        $("#hobbiesWrapper").$(byText("Reading")).click();
        // Upload picture
        $("#uploadPicture").uploadFromClasspath("lesson3/knight.jpg");
        // Fill 'Current Address'
        $("#currentAddress").setValue("Barovia, Ravenloft Castle, Area K86 \"Strahd's Tomb\"");
        // Fill 'State and City'
        $("#state").click();
        $(byText("NCR")).click();
        $("#city").click();
        $(byText("Delhi")).click();

        $("#submit").scrollTo().click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $("#closeLargeModal").shouldBe(visible);

        $(".table-responsive").$("thead").shouldHave(text("Label"));
        $(".table-responsive").$("thead").shouldHave(text("Values"));

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Strahd von Zarovich"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("strahd@barovia.dom"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("8005553535"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("01 January,2000"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Maths"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Reading"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("knight.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Barovia, Ravenloft Castle, Area K86 \"Strahd's Tomb\""));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("NCR Delhi"));
    }
}
