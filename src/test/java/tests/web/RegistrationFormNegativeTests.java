package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.Variables.*;
import static tests.data.Variables.userNumber;

public class RegistrationFormNegativeTests {

    @Nested
    class webRequiredFormTests {

        @BeforeAll
        static void setUp() {
            Configuration.browserSize = "1920x1080";
            Configuration.browser = "chrome";
            Configuration.baseUrl = "https://demoqa.com/";
        }

        @Test
        void successMissingRequiredFirstNameTests() {
            open("automation-practice-form");
            $("#lastName").setValue(lastName);
            $("#genterWrapper").$(byText(genterWrapper)).click();
            $("#userNumber").setValue(userNumber);

            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @Test
        void successMissingRequiredLastNameTests() {
            open("automation-practice-form");
            $("#firstName").setValue(firstName);
            $("#genterWrapper").$(byText(genterWrapper)).click();
            $("#userNumber").setValue(userNumber);

            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @Test
        void successMissingRequiredGenterWrapperTests() {
            open("automation-practice-form");
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#userNumber").setValue(userNumber);

            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @Test
        void successMissingRequiredUserNumberTests() {
            open("automation-practice-form");
            $("#firstName").setValue(firstName);
            $("#lastName").setValue(lastName);
            $("#genterWrapper").$(byText(genterWrapper)).click();

            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @AfterAll
        static void teaDown() {
            Selenide.closeWebDriver();
        }
    }
}