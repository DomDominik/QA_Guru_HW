package tests.web.parametrized;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import tests.pages.RegistrationPage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;

public class RequiredFieldFormTests {
    RegistrationPage registrationPage = new RegistrationPage();


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.browser ="Chrome";
        Configuration.browserVersion = "128";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
    @BeforeEach
    void setupAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
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
        step("Открываем страницу регистрации", () -> {
            registrationPage
                    .openPege()
                    .removeBanner();
        });
        step("Заполняем страницу регистрации", () -> {
            registrationPage
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .genderWrapperParametrized(genderWrapper)
                    .typeUserNumber(userNumber)
                    .submitForm();
        });
        step("Проверяем видимость страницы регистрации", () ->
            registrationPage.checkNotVisibleTableResponsive());
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }
}

