package tests.data;

import com.github.javafaker.Faker;
import tests.data.utils.RandomUtils;

import java.util.Locale;


public class RandomizTestData {

    public static String
            firstRandomName,
            lastRandomName,
            userRandomEmail,
            genterRandomWrapper,
            userRandomNumber,
            yearOfBirthRandom,
            monthOfBirthRandom,
            dayOfBirthRandom,
            subjectsRandom,
            hobbiesRandom,
            currentAddressRandom,
            permanentAddressRandom,
            countryRandom,
            cityRandom;

    static {
        Faker faker = new Faker(Locale.of("en"));
        String[] birthDate = RandomUtils.generateBirthDate();
        CountryAndCity countryAndCity = faker.options().nextElement(CountryAndCity.values());


        firstRandomName = faker.name().firstName();
        lastRandomName = faker.name().lastName();
        userRandomEmail = faker.internet().emailAddress();
        genterRandomWrapper = faker.options().option("Male", "Female", "Other");
        userRandomNumber = faker.number().digits(10);
        // Получаем дату рождения
        yearOfBirthRandom = birthDate[0];
        monthOfBirthRandom = birthDate[1];
        dayOfBirthRandom = birthDate[2];
        //
        subjectsRandom = Subject.getRandomSubject();
        hobbiesRandom = faker.options().option("Sports", "Reading", "Music");
        currentAddressRandom = faker.address().fullAddress();
        permanentAddressRandom = faker.address().fullAddress();

        countryRandom = countryAndCity.getCountry();
        cityRandom = countryAndCity.getRandomCity(faker);
    }
}
