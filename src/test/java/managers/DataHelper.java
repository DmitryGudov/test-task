package managers;

import com.github.javafaker.Faker;

import java.util.Locale;
import java.util.Random;

public class DataHelper {

    private static final Faker faker = new Faker();
    private static final Faker fakerRus = new Faker(new Locale("ru"));
    private static final Random random = new Random();

    public static String getRandomEmail() {
        return faker.internet().emailAddress();
    }

    public static String getRandomRusEmail() {
        return fakerRus.internet().emailAddress();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }

    public static String getRandomName() {
        return faker.name().firstName();
    }

    public static String getRandomRusName() {
        return fakerRus.name().firstName();
    }

    public static String getRandomEmailWithLocale() {
        return random.nextBoolean() ? getRandomEmail() : getRandomRusEmail();
    }

    public static String getRandomNameWithLocale() {
        return random.nextBoolean() ? getRandomName() : getRandomRusName();
    }

    public static String getRandomRusGender() {
        String[] genders = {"Мужской", "Женский"};
        return genders[random.nextInt(genders.length)];
    }

}
