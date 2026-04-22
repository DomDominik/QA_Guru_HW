package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.of;


import tests.data.CountryAndCity;
import tests.data.Subject;
import tests.pages.RegistrationPage;

import java.util.Arrays;
import java.util.stream.Stream;

import static tests.data.TestData.*;
import static tests.data.TestData.userNumber;

@DisplayName("Класс с параметризованными тестами")
public class ParametrizedRegistrationFormTests {
    RegistrationPage registrationPage = new RegistrationPage();


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";

    }
    @Tag("Web")
    @Tag("Smoke")
    @CsvSource({
            "'', 'Иванов', 'Female', '7445655716'",
            "'Иван', '', 'Male', '7445655716'",
            "'Иван', 'Иванов', '', '7445655716'",
            "'Иван', 'Иванов', 'Other', ''"
    })
    @DisplayName("Тесты на обязательность полей")
    @ParameterizedTest(name = "Имя: {0}, Фамилия: {1}, пол: {2}, Телефон: {3}")
   void successMissingRequiredTests(
           String firstName,
           String lastName,
           String genderWrapper,
           String userNumber
    ) {
        registrationPage
                .openPege()
                .removeBanner()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .genderWrapperParametrized(genderWrapper)
                .typeUserNumber(userNumber)
                .submitForm()

                .checkNotVisibleTableResponsive();
    }
    @DisplayName("Тесты на варианты Subjects")
    @ParameterizedTest (name = "[{index}] Тест с предметом: {0}")
    @EnumSource(Subject.class)
    void successRegistrationWithDifferanceSubject(Subject subject) {
        registrationPage
                .openPege()
                .removeBanner()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeGenderWrapper(genterWrapper)
                .typeUserNumber(userNumber)
                .typeSubjectsInput(subject.getDisplayName())
                .submitForm()

                .checkVisibleTableResponsive()
                .checkResult("Student Name", firstName+" "+lastName)
                .checkResult("Gender", genterWrapper)
                .checkResult("Mobile", userNumber)
                .checkResult("Subjects", subject.getDisplayName());
    }
    static Stream<Arguments> countryAndCityProvider() {
        return Arrays.stream(CountryAndCity.values())
                .flatMap(countryAndCity ->
                        Arrays.stream(countryAndCity.getCities())
                                .map(city -> of(countryAndCity.getCountry(), city))
                );
    }
    @DisplayName("Тесты на варианты State and City")
    @ParameterizedTest(name = "Страна: {0}, Город: {1}")
    @MethodSource("countryAndCityProvider")
    void successTestsWithDifferanceStateAndCity(String country, String city) {
        registrationPage
                .openPege()
                .removeBanner()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeGenderWrapper(genterWrapper)
                .typeUserNumber(userNumber)
                .typeCountrySelect(country)
                .typeCitySelect(city)
                .submitForm()

                .checkVisibleTableResponsive()
                .checkResult("Student Name", firstName+" "+lastName)
                .checkResult("Gender", genterWrapper)
                .checkResult("Mobile", userNumber)
                .checkResult("State and City", country+" "+city);
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }
    }
