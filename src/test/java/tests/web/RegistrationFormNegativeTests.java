package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
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
        void successMissingRequiredFirstNameTests() {
            registrationPage
                    .openPege()
                    .typeLastName(lastName)
                    .typeGenterWrapper(genterWrapper)
                    .typeUserNumber(userNumber)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @Test
        void successMissingRequiredLastNameTests() {
            registrationPage
                    .openPege()
                    .typeFirstName(firstName)
                    .typeGenterWrapper(genterWrapper)
                    .typeUserNumber(userNumber)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @Test
        void successMissingRequiredGenterWrapperTests() {
            registrationPage
                    .openPege()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeUserNumber(userNumber)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @Test
        void successMissingRequiredUserNumberTests() {
            registrationPage
                    .openPege()
                    .typeFirstName(firstName)
                    .typeLastName(lastName)
                    .typeGenterWrapper(genterWrapper)
                    .submitForm()

                    .checkNotVisibleTableResponsive();
        }

        @AfterAll
        static void teaDown() {
            Selenide.closeWebDriver();
        }
    }
}