package tests.web.parametrized;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import tests.config.TestConfig;
import tests.data.CountryAndCity;
import tests.helpers.Attachments;
import tests.pages.RegistrationPage;

import java.util.Arrays;
import java.util.stream.Stream;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static org.junit.jupiter.params.provider.Arguments.of;
import static tests.data.TestData.*;
import static tests.data.TestData.userNumber;

public class StateAndCityFieldFormTest {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setUp() {
        TestConfig.applyConfiguration();
    }
    @BeforeEach
    void setupAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    static Stream<Arguments> countryAndCityProvider() {
        return Arrays.stream(CountryAndCity.values())
                .flatMap(countryAndCity ->
                        Arrays.stream(countryAndCity.getCities())
                                .map(city -> of(countryAndCity.getCountry(), city))
                );
    }
    @Tag("Smoke")
    @DisplayName("Тесты на варианты State and City")
    @ParameterizedTest(name = "Страна: {0}, Город: {1}")
    @MethodSource("countryAndCityProvider")
    void successTestsWithDifferanceStateAndCity(String country, String city) {
        step("Открываем страницу регистрации", () -> {
            registrationPage
                    .openPege()
                    .removeBanner();
        });
        step("Заполняем страницу регистрации", () -> {
            registrationPage
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeGenderWrapper(genterWrapper)
                    .typeUserNumber(userNumber)
                    .typeCountrySelect(country)
                    .typeCitySelect(city)
                    .submitForm();
        });
        step("Проверяем видимость и содержание страницы регистрации", () -> {
            registrationPage
                    .checkVisibleTableResponsive()
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Gender", genterWrapper)
                    .checkResult("Mobile", userNumber)
                    .checkResult("State and City", country + " " + city);
        });
    }
    @AfterEach
    void reportsFactureAndTearDown() {
        Attachments.screenshotAs("Скриншот формы регистрации");
        Attachments.addVideo();
        Attachments.browserConsoleLogs();
        Attachments.getVideoUrl();
        Attachments.pageSource();

        Selenide.closeWebDriver();
        SelenideLogger.removeListener("allure");
    }
}
