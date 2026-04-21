package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import tests.data.RandomizTestData;
import tests.pages.RegistrationPage;

import static tests.data.TestData.*;

public class RegistrationFormPositiveTests {
    RegistrationPage registrationPage = new RegistrationPage();
    private RandomizTestData randomData;


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";

    }
    @Test
    @DisplayName("Тест на большую форму регистрации -> заданные данные -> обязательные поля")
    void successRequiredFormTests() {

        registrationPage
                .openPege()
                .removeBanner()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeGenderWrapper(genterWrapper)
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
    @DisplayName("Тест на большую форму регистрации -> заданные данные -> все поля")
    void successFullFormTests() {
        registrationPage
                .openPege()
                .removeBanner()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeUserEmail(userEmail)
                .typeGenderWrapper(genterWrapper)
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
    @BeforeEach
    public void setRandomDataUp() {
        randomData = new RandomizTestData();
    }
    @Test
    @DisplayName("Тест на большую форму регистрации -> рандомные данные -> все поля")
    void successFullFormRandomizTests() {
        registrationPage
                .openPege()
                .removeBanner()
                .typeFirstName(randomData.firstRandomName)
                .typeLastName(randomData.lastRandomName)
                .typeUserEmail(randomData.userRandomEmail)
                .typeGenderWrapper(randomData.genterRandomWrapper)
                .typeUserNumber(randomData.userRandomNumber)
                .typeDateOfBirth(randomData.monthOfBirthRandom,
                        randomData.yearOfBirthRandom,
                        randomData.dayOfBirthRandom) //Работа с календарем
                .typeSubjectsInput(randomData.subjectsRandom)
                .typeHobbiesRadioButton(randomData.hobbiesRandom)
                .typeCurrentAddress(randomData.currentAddressRandom)
                .typeCountrySelect(randomData.countryRandom)
                .typeCitySelect(randomData.cityRandom)
                .submitForm()

                .checkVisibleTableResponsive()
                .checkResult("Student Name", randomData.firstRandomName+
                        " "+ randomData.lastRandomName)
                .checkResult("Student Email", randomData.userRandomEmail)
                .checkResult("Gender", randomData.genterRandomWrapper)
                .checkResult("Mobile", randomData.userRandomNumber)
                .checkResult("Date of Birth", randomData.dayOfBirthRandom+
                        " "+ randomData.monthOfBirthRandom+ "," + randomData.yearOfBirthRandom)
                .checkResult("Subjects", randomData.subjectsRandom)
                .checkResult("Hobbies", randomData.hobbiesRandom)
                .checkResult("Address", randomData.currentAddressRandom)
                .checkResult("State and City", randomData.countryRandom+" "+randomData.cityRandom);
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }
}
