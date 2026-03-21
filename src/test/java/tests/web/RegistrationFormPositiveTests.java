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

public class RegistrationFormPositiveTests {
    String firstName = "Dominik";
    String lastName = "Smith";
    String userEmail = "dominik@gmail.com";
    String genterWrapper = "Male";
    String userNumber = "7981688899";

    String currentAddress = "Академическая 3";


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

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Dominik Smith"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldBe();
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("7981688899"));
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
        $(".react-datepicker__year-select").selectOption("2026");// Выбираем год
        $(".react-datepicker__month-select").selectOption("November");// Выбираем месяц
        $(".react-datepicker__day.react-datepicker__day--005").click();// Выбираем день
        //
        $("#subjectsInput").setValue("Chemistry").pressEnter();
        $("#hobbiesWrapper").$(byText("Sports")).click();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        // загрузка файла
        $("#uploadPicture").uploadFromClasspath("kartina-edvard-hopper.jpg");
        //
        $("#currentAddress").setValue(currentAddress);
        $("#react-select-3-input").setValue("Haryana").pressEnter();
        $("#react-select-4-input").setValue("Karnal").pressEnter();
        $("#submit").click();

        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text("Dominik Smith"));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text("dominik@gmail.com"));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text("Male"));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text("7981688899"));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text("05 November,2026"));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text("Chemistry"));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text("Sports, Reading"));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text("kartina-edvard-hopper.jpg"));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text("Академическая 3"));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text("Haryana Karnal"));
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }
}
