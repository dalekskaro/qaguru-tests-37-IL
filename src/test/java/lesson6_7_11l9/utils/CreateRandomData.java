package lesson6_7_11l9.utils;

import com.github.javafaker.Faker;

import java.io.IOException;
import java.util.Locale;

public class CreateRandomData {
    static Faker faker = new Faker(new Locale("en"));

    public static String getFirstName() {
        return faker.name().firstName();
    }

    public static String getLastName() {
        return faker.name().lastName();
    }

    public static String getEmail() {
        return faker.internet().emailAddress();
    }

    public static String getGender() {
        return faker.options().option("Male", "Female", "Other");
    }

    public static String getMobileNumber() {
        return faker.number().digits(10);
    }

    public static String getDayAsString() {
        return String.format("%s", faker.number().numberBetween(10, 28));
    }

    public static String getMonthAsString() {
        return faker.options().option("January", "February", "March", "April",
                "May", "June", "July", "August", "September", "October", "November", "December");
    }

    public static String getYearAsString() {
        return String.format("%s", faker.number().numberBetween(1920, 2024));
    }

    public static String getSubject() throws IOException {
        return RandomUtils.getRandomFromFile("lesson7/subject.txt");
    }

    public static String getHobbies() throws IOException {
        return RandomUtils.getRandomFromFile("lesson7/hobbies.txt");
    }

    public static String getPicture() {
        return faker.lorem().characters(5);
    }

    public static String getAddress() {
        return faker.harryPotter().location();
    }

    public static String getState() {
        return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
    }

    public static String getCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> "none";
        };
    }
}
