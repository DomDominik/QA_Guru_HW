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
            $("#lastName").setValue("Smith");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("7981688899");
            // input с датой
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption("2026");// Выбираем год
            $(".react-datepicker__month-select").selectOption("November");// Выбираем месяц
            $(".react-datepicker__day.react-datepicker__day--005").click();// Выбираем день
            //
            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @Test
        void successMissingRequiredLastNameTests() {
            open("automation-practice-form");
            $("#firstName").setValue("Dominik");
            $("#genterWrapper").$(byText("Male")).click();
            $("#userNumber").setValue("7981688899");
            // input с датой
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption("2026");// Выбираем год
            $(".react-datepicker__month-select").selectOption("November");// Выбираем месяц
            $(".react-datepicker__day.react-datepicker__day--005").click();// Выбираем день
            //
            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @Test
        void successMissingRequiredGenterWrapperTests() {
            open("automation-practice-form");
            $("#firstName").setValue("Dominik");
            $("#lastName").setValue("Smith");
            $("#userNumber").setValue("7981688899");
            // input с датой
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption("2026");// Выбираем год
            $(".react-datepicker__month-select").selectOption("November");// Выбираем месяц
            $(".react-datepicker__day.react-datepicker__day--005").click();// Выбираем день
            //
            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @Test
        void successMissingRequiredUserNumberTests() {
            open("automation-practice-form");
            $("#firstName").setValue("Dominik");
            $("#lastName").setValue("Smith");
            $("#genterWrapper").$(byText("Male")).click();
            // input с датой
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption("2026");// Выбираем год
            $(".react-datepicker__month-select").selectOption("November");// Выбираем месяц
            $(".react-datepicker__day.react-datepicker__day--005").click();// Выбираем день
            //
            $("#submit").click();

            $("#example-modal-sizes-title-lg").shouldNotBe(visible);
        }

        @AfterAll
        static void teaDown() {
            Selenide.closeWebDriver();
        }
    }
}