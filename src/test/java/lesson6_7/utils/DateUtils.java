package lesson6_7.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class DateUtils {
    public static DayOfMonthAndYear dateTranslation(Date date) {
        LocalDateTime ldt = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        String day = ldt.format(DateTimeFormatter.ofPattern("dd"));
        System.out.println("##### day: " + day);
        String month = ldt.format(DateTimeFormatter.ofPattern("MMMM", Locale.ENGLISH));
        System.out.println("##### month: " + month);
        String year = ldt.format(DateTimeFormatter.ofPattern("yyyy"));
        System.out.println("##### year: " + year);

        return new DayOfMonthAndYear(day, month, year);
    }
}
