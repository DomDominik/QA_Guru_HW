package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.pages.RegistrationPage;

import static tests.data.RandomizTestData.*;
import static tests.data.TestData.*;

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
                .checkResult("Student Name", firstName+" "+lastName)
                .checkEmptyResult("Student Email")
                .checkResult("Gender", genterWrapper)
                .checkResult("Mobile", userNumber)
                .checkEmptyResult("Date of Birth")
                .checkEmptyResult("Subjects")
                .checkEmptyResult("Hobbies")
                .checkEmptyResult("Picture")
                .checkEmptyResult("Address")
                .checkEmptyResult("State and City");
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
                .typeDateOfBirth(monthOfBirth, yearOfBirth, dayOfBirth) //Работа с календарем
                .typeSubjectsInput(subjects)
                .typeHobbiesRadioButton(hobbiesSports)
                .typeHobbiesRadioButton(hobbiesReading)
                .typeUploadPicture(nameOfFile) // загрузка файла
                .typeCurrentAddress(currentAddress)
                .typeCountrySelect(country)
                .typeCitySelect(city)
                .submitForm()

                .checkVisibleTableResponsive()
                .checkResult("Student Name", firstName+" "+lastName)
                .checkResult("Student Email", userEmail)
                .checkResult("Gender", genterWrapper)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayOfBirth+" "+monthOfBirth+","+yearOfBirth)
                .checkResult("Subjects", subjects)
                .checkResult("Hobbies", hobbiesSports+", "+hobbiesReading)
                .checkResult("Picture", nameOfFile)
                .checkResult("Address", currentAddress)
                .checkResult("State and City", country+" "+city);
    }
    @Test    void successFullFormRandomizTests() {
        registrationPage
                .openPege()
                .typeFirstName(firstRandomName)
                .typeLastName(lastRandomName)
                .typeUserEmail(userRandomEmail)
                .typeGenterWrapper(genterRandomWrapper)
                .typeUserNumber(userRandomNumber)
                .typeDateOfBirth(monthOfBirthRandom, yearOfBirthRandom, dayOfBirthRandom) //Работа с календарем
                .typeSubjectsInput(subjectsRandom)
                .typeHobbiesRadioButton(hobbiesRandom)
                .typeCurrentAddress(currentAddressRandom)
                .typeCountrySelect(countryRandom)
                .typeCitySelect(cityRandom)
                .submitForm()

                .checkVisibleTableResponsive()
                .checkResult("Student Name", firstRandomName+" "+lastRandomName)
                .checkResult("Student Email", userRandomEmail)
                .checkResult("Gender", genterRandomWrapper)
                .checkResult("Mobile", userRandomNumber)
                .checkResult("Date of Birth", dayOfBirthRandom+" "+monthOfBirthRandom+","+yearOfBirthRandom)
                .checkResult("Subjects", subjectsRandom)
                .checkResult("Hobbies", hobbiesRandom)
                .checkResult("Address", currentAddressRandom)
                .checkResult("State and City", countryRandom+" "+cityRandom);
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }
}
