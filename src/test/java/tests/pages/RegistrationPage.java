package tests.pages;

import com.codeborne.selenide.SelenideElement;
import tests.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();

    //Elements
    private SelenideElement firstNameInput = $("#firstName");
    private SelenideElement lastNameInput = $("#lastName");
    private SelenideElement userEmailInput = $("#userEmail");
    private SelenideElement genterContainer = $("#genterWrapper");
    private SelenideElement userNumberInput = $("#userNumber");
    private SelenideElement subjectsInput = $("#subjectsInput");
    private SelenideElement hobbiesRadioButton = $("#hobbiesWrapper");
    private SelenideElement uploadPicture = $("#uploadPicture");
    private SelenideElement currentAddressInput = $("#currentAddress");
    private SelenideElement countrySelect = $("#react-select-3-input");
    private SelenideElement citySelect = $("#react-select-4-input");
    private SelenideElement submitButton = $("#submit");
    private SelenideElement tableResponsive = $(".table-responsive");
    private SelenideElement tablemModalResponsive = $("#example-modal-sizes-title-lg");


    //Actions
    public RegistrationPage openPege () {
        open("automation-practice-form");

        return  this;
    }
    public RegistrationPage typeFirstName (String value) {
        firstNameInput.setValue(value);

        return  this;
    }
    public RegistrationPage typeLastName (String value) {
        lastNameInput.setValue(value);

        return  this;
    }
    public RegistrationPage typeUserEmail (String value) {
        userEmailInput.setValue(value);

        return  this;
    }
    public RegistrationPage typeGenterWrapper (String value) {
        genterContainer.$(byText(value)).click();

        return  this;
    }
    public RegistrationPage typeUserNumber (String value) {
        userNumberInput.setValue(value);

        return  this;
    }
    public RegistrationPage typeDateOfBirth (String month, String year, String day) {
        $("#dateOfBirthInput").click();
        calendarComponent.setDate(month, year, day);
        return  this;
    }
    public RegistrationPage typeSubjectsInput (String value) {
        subjectsInput.setValue(value).pressEnter();

        return  this;
    }
    public RegistrationPage typeHobbiesRadioButton (String value) {
        hobbiesRadioButton.$(byText(value)).click();

        return  this;
    }
    public RegistrationPage typeUploadPicture (String value) {
        uploadPicture.uploadFromClasspath(value);

        return  this;
    }
    public RegistrationPage typeCurrentAddress (String value) {
        currentAddressInput.setValue(value);

        return  this;
    }
    public RegistrationPage typeCountrySelect (String value) {
        countrySelect.setValue(value).pressEnter();

        return  this;
    }
    public RegistrationPage typeCitySelect (String value) {
        citySelect.setValue(value).pressEnter();

        return  this;
    }
    public RegistrationPage submitForm () {
        submitButton.click();

        return this;
    }
    public RegistrationPage checkTableResponsive (String label, String expectedValue) {
        tableResponsive
                .$(byText(label))
                .parent()
                .shouldHave(text(expectedValue));

        return this;
    }
    public RegistrationPage checkEmptyTableResponsive (String label, String expectedValue) {
        tableResponsive
                .$(byText(label))
                .parent()
                .shouldBe();

        return this;
    }
    public RegistrationPage checkVisibleTableResponsive () {
        tablemModalResponsive
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));

        return this;
    }
    public RegistrationPage checkNotVisibleTableResponsive () {
        tablemModalResponsive
                .shouldNotBe(visible);

        return this;
    }
}
