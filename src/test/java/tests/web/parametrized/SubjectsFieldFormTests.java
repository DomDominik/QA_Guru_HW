package tests.web.parametrized;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import tests.config.TestConfig;
import tests.data.Subject;
import tests.helpers.Attachments;
import tests.pages.RegistrationPage;

import static com.codeborne.selenide.logevents.SelenideLogger.step;
import static tests.data.TestData.*;
import static tests.data.TestData.userNumber;

public class SubjectsFieldFormTests {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setUp() {
        TestConfig.applyConfiguration();
    }
    @BeforeEach
    void setupAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @Tag("Smoke")
    @DisplayName("Тесты на варианты Subjects")
    @ParameterizedTest(name = "[{index}] Тест с предметом: {0}")
    @EnumSource(Subject.class)
    void successRegistrationWithDifferanceSubject(Subject subject) {
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
                    .typeSubjectsInput(subject.getDisplayName())
                    .submitForm();
        });
        step("Проверяем видимость и содержание страницы регистрации", () -> {
            registrationPage
                    .checkVisibleTableResponsive()
                    .checkResult("Student Name", firstName + " " + lastName)
                    .checkResult("Gender", genterWrapper)
                    .checkResult("Mobile", userNumber)
                    .checkResult("Subjects", subject.getDisplayName());
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
