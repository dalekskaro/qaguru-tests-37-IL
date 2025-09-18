package lesson6_7_11l9.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class ModalTableComponent {

  SelenideElement submittingModalLabel = $("#example-modal-sizes-title-lg"),
      submittingModalTable = $(".table-responsive");

  public void checkDataInSubmittingModalTable(String label, String value) {
    submittingModalTable.$(byText(label)).parent().shouldHave(text(value));
  }

  public void checkDateInSubmittingModalTable(String label, String birthDay) {
    DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d MMMM uuuu", Locale.ENGLISH);
    LocalDate date = LocalDate.parse(birthDay, inputFormatter);

    DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMMM,uuuu", Locale.ENGLISH);
    String formatted = date.format(outputFormatter);

    submittingModalTable.$(byText(label)).parent().shouldHave(text(formatted));
  }

  public void checkResultInSubmittingModalLabel(String value) {
    submittingModalLabel.shouldHave(text(value));
  }

  public void checkResultInSubmittingModalTableThead(String value) {
    submittingModalTable.$("thead").shouldHave(text(value));
  }

  public void checkDataDateInSubmittingModalTable(String label, String date) {
    submittingModalTable.$(byText(label)).parent().shouldHave(text(date));
  }
}
