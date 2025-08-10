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

        $(".table-responsive table").$("thead").shouldHave(
                text("Label"),
                text("Values")
        );

        $(".table-responsive table").$("tbody").shouldHave(
                text("Student Name"),
                text("Student Email"),
                text("Gender"),
                text("Mobile"),
                text("Date of Birth"),
                text("Subjects"),
                text("Hobbies"),
                text("Picture"),
                text("Address"),
                text("State and City")
        );

        $(".table-responsive table").$("tbody").shouldHave(
                text("Strahd von Zarovich"),
                text("strahd@barovia.dom"),
                text("Male"),
                text("8005553535"),
                text("01 January,2000"),
                text("Maths"),
                text("Reading"),
                text("knight.jpg"),
                text("Barovia, Ravenloft Castle, Area K86 \"Strahd's Tomb\""),
                text("NCR Delhi")
        );
    }
}
