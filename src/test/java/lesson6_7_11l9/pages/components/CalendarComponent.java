package lesson6_7_11l9.pages.components;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import com.codeborne.selenide.SelenideElement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import lesson6_7_11l9.utils.CreateRandomData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarComponent {

  private final SelenideElement
      yearSelect = $(".react-datepicker__year-select"),
      monthSelect = $(".react-datepicker__month-select");

  private final String dayZero = ".react-datepicker__day--0";

  private static final Logger log = LoggerFactory.getLogger(CreateRandomData.class);

  public void setDate(String day, String month, String year) {
    yearSelect.$(byText(year)).click();
    monthSelect.$(byText(month)).click();
    $(dayZero + day).click();
  }

  public void setDateWhenOneValue(String birthDay) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM uuuu", Locale.ENGLISH);
    LocalDate date = LocalDate.parse(birthDay, formatter);

    String day = date.format(DateTimeFormatter.ofPattern("dd", Locale.ENGLISH));
    String month = date.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
    String year = String.valueOf(date.getYear());

    yearSelect.$(byText(year)).click();
    monthSelect.$(byText(month)).click();
    $(dayZero + day).click();
  }
}
