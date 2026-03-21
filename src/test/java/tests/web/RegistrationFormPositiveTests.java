package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.Variables.*;

public class RegistrationFormPositiveTests {

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";
    }
    @Test
    void successRequiredFormTests() {
        open("automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#genterWrapper").$(byText(genterWrapper)).click();
        $("#userNumber").setValue(userNumber);

        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName+" "+lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldBe();
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genterWrapper));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldBe();
        $(".table-responsive").$(byText("Subjects")).parent().shouldBe();
        $(".table-responsive").$(byText("Hobbies")).parent().shouldBe();
        $(".table-responsive").$(byText("Picture")).parent().shouldBe();
        $(".table-responsive").$(byText("Address")).parent().shouldBe();
        $(".table-responsive").$(byText("State and City")).parent().shouldBe();
    }
    @Test
    void successFullFormTests() {
        open("automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#genterWrapper").$(byText(genterWrapper)).click();
        $("#userNumber").setValue(userNumber);
        // input с датой
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);// Выбираем год
        $(".react-datepicker__month-select").selectOption(monthOfBirth);// Выбираем месяц
        $(".react-datepicker__day.react-datepicker__day--005").click();// Выбираем день
        //
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbiesWrapper").$(byText(hobbiesSports)).click();
        $("#hobbiesWrapper").$(byText(hobbiesReading)).click();
        // загрузка файла
        $("#uploadPicture").uploadFromClasspath(nameOfFile);
        //
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue(country).pressEnter();
        $("#react-select-4-input").setValue(city).pressEnter();
        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName+" "+lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genterWrapper));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("05"+" "+monthOfBirth+","+yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjects));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbiesSports+", "+hobbiesReading));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(nameOfFile));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(country+" "+city));
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }
}
