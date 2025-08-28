package lesson6_7.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ModalTableComponent {

    SelenideElement submittingModalLabel = $("#example-modal-sizes-title-lg"),
            submittingModalTable = $(".table-responsive");

    public void checkDataInSubmittingModalTable(String label, String value) {
        submittingModalTable.$(byText(label)).parent().shouldHave(text(value));
    }

    public void checkResultInSubmittingModalLabel(String value) {
        submittingModalLabel.shouldHave(text(value));
    }

    public void checkResultInSubmittingModalTableThead(String value) {
        submittingModalTable.$("thead").shouldHave(text(value));
    }

    public void checkDataDateInSubmittingModalTable(String label, String date){
        submittingModalTable.$(byText(label)).parent().shouldHave(text(date));
    }
}
