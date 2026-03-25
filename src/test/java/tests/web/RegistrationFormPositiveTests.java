package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.pages.RegistrationPage;
import tests.pages.TexBoxPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static tests.data.Variables.*;

public class RegistrationFormPositiveTests {
    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";
    }
    @Test
    void successRequiredFormTests() {
        registrationPage
                .openPege()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeGenterWrapper(genterWrapper)
                .typeUserNumber(userNumber)
                .submitForm()

                .checkVisibleTableResponsive()
                .checkTableResponsive("Student Name", firstName+" "+lastName)
                .checkEmptyTableResponsive("Student Email", "")
                .checkTableResponsive("Gender", genterWrapper)
                .checkTableResponsive("Mobile", userNumber)
                .checkEmptyTableResponsive("Date of Birth", "")
                .checkEmptyTableResponsive("Subjects", "")
                .checkEmptyTableResponsive("Hobbies", "")
                .checkEmptyTableResponsive("Picture", "")
                .checkEmptyTableResponsive("Address", "")
                .checkEmptyTableResponsive("State and City", "");
    }
    @Test
    void successFullFormTests() {
        registrationPage
                .openPege()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .typeGenterWrapper(genterWrapper)
                .typeUserNumber(userNumber)
                .typeDateOfBirth(monthOfBirth, yearOfBirth, "5") //Работа с календарем
                .typeSubjectsInput(subjects)
                .typeHobbiesRadioButton(hobbiesSports)
                .typeHobbiesRadioButton(hobbiesReading)
                .typeUploadPicture(nameOfFile) // загрузка файла
                .typeCurrentAddress(currentAddress)
                .typeCountrySelect(country)
                .typeCitySelect(city)
                .submitForm()

                .checkVisibleTableResponsive()
                .checkTableResponsive("Student Name", firstName+" "+lastName)
                .checkTableResponsive("Student Email", userEmail)
                .checkTableResponsive("Gender", genterWrapper)
                .checkTableResponsive("Mobile", userNumber)
                .checkTableResponsive("Date of Birth", "05"+" "+monthOfBirth+","+yearOfBirth)
                .checkTableResponsive("Subjects", subjects)
                .checkTableResponsive("Hobbies", hobbiesSports+", "+hobbiesReading)
                .checkTableResponsive("Picture", nameOfFile)
                .checkTableResponsive("Address", currentAddress)
                .checkTableResponsive("State and City", country+" "+city);
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }
}
