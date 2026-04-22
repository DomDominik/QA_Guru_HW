package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import tests.pages.RegistrationPage;

import static tests.data.TestData.*;

public class RegistrationFormNegativeTests {

    @Nested
    class webRequiredFormTests {
        RegistrationPage registrationPage = new RegistrationPage();

        @BeforeAll
        static void setUp() {
            Configuration.browserSize = "1920x1080";
            Configuration.browser = "chrome";
            Configuration.baseUrl = "https://demoqa.com/";
        }

        @Test
        @DisplayName("Тест на большую форму регистрации -> обязательность полей -> без firstName")
        void successMissingRequiredFirstNameTests() {
            registrationPage
                    .openPege()
                    .removeBanner()
                    .typeLastName(lastName)
                    .typeGenderWrapper(genterWrapper)
                    .typeUserNumber(userNumber)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @Test
        @DisplayName("Тест на большую форму регистрации -> обязательность полей -> без lastName")
        void successMissingRequiredLastNameTests() {
            registrationPage
                    .openPege()
                    .removeBanner()
                    .typeFirstName(firstName)
                    .typeGenderWrapper(genterWrapper)
                    .typeUserNumber(userNumber)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @Test
        @DisplayName("Тест на большую форму регистрации -> обязательность полей -> без genterWrapper")
        void successMissingRequiredGenterWrapperTests() {
            registrationPage
                    .openPege()
                    .removeBanner()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeUserNumber(userNumber)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @Test
        @DisplayName("Тест на большую форму регистрации -> обязательность полей -> без userNumber")
        void successMissingRequiredUserNumberTests() {
            registrationPage
                    .openPege()
                    .removeBanner()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeGenderWrapper(genterWrapper)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @AfterAll
        static void teaDown() {
            Selenide.closeWebDriver();
        }
    }
}