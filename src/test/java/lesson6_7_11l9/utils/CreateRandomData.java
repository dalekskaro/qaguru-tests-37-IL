package lesson6_7_11l9.utils;

import com.github.javafaker.Faker;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateRandomData {

  private static final Logger log = LoggerFactory.getLogger(CreateRandomData.class);
  static Faker faker = new Faker(new Locale("en"));

  public static String getFirstName() {
    String firstName = faker.name().firstName();
    LogUtils.logVariable("firstName", firstName, log);
    return firstName;
  }

  public static String getLastName() {
    String lastName = faker.name().lastName();
    LogUtils.logVariable("lastName", lastName, log);
    return lastName;
  }

  public static String getEmail() {
    String email = faker.internet().emailAddress();
    LogUtils.logVariable("email", email, log);
    return email;
  }

  public static String getGender() {
    String gender = faker.options().option("Male", "Female", "Other");
    LogUtils.logVariable("gender", gender, log);
    return gender;
  }

  public static String getMobileNumber() {
    String mobileNumber = faker.number().digits(10);
    LogUtils.logVariable("mobileNumber", mobileNumber, log);
    return mobileNumber;
  }

  public static String getBirthDayAsString() {
    Date oldDate = faker.date().birthday(1, 80); //oldDate: Sat Dec 15 00:44:37 MSK 2007
    LocalDate localDate = oldDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //localDate: 2007-12-15
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy",
        Locale.ENGLISH); //formatter: Value(DayOfMonth,2)' 'Text(MonthOfYear)' 'Value(YearOfEra,4,19,EXCEEDS_PAD)
    LogUtils.logVariable("birthDay", localDate.format(formatter), log);
    return localDate.format(formatter); //localDate.format(formatter): 15 December 2007
  }

  public static String getDayAsString() {
    String day = String.format("%s", faker.number().numberBetween(10, 28));
    LogUtils.logVariable("day", day, log);
    return day;
  }

  public static String getMonthAsString() {
    String month = faker.options().option("January", "February", "March", "April",
        "May", "June", "July", "August", "September", "October", "November", "December");
    LogUtils.logVariable("month", month, log);
    return month;
  }

  public static String getYearAsString() {
    String year = String.format("%s", faker.number().numberBetween(1920, 2024));
    LogUtils.logVariable("year", year, log);
    return year;
  }

  public static String getSubject() throws IOException {
    String subject = RandomUtils.getRandomFromFile("lesson7/subject.txt");
    LogUtils.logVariable("subject", subject, log);
    return subject;
  }

  public static String getHobbies() throws IOException {
    String hobbies = RandomUtils.getRandomFromFile("lesson7/hobbies.txt");
    LogUtils.logVariable("hobbies", hobbies, log);
    return hobbies;
  }

  public static String getPicture() {
    String picture = faker.lorem().characters(5);
    LogUtils.logVariable("picture", picture, log);
    return picture;
  }

  public static String getAddress() {
    String address = faker.harryPotter().location();
    LogUtils.logVariable("address", address, log);
    return address;
  }

  public static String getState() {
    String state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    LogUtils.logVariable("state", state, log);
    return state;
  }

  public static String getCity(String state) {
    String city = switch (state) {
      case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
      case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
      case "Haryana" -> faker.options().option("Karnal", "Panipat");
      case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
      default -> "none";
    };
    LogUtils.logVariable("city", city, log);
    return city;
  }
}
