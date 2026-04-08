package tests.pages;

import com.codeborne.selenide.SelenideElement;
import tests.data.utils.RemoveBannerUtils;
import tests.pages.components.CalendarComponent;
import tests.pages.components.ModalResultComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    ModalResultComponent modalResultComponent = new ModalResultComponent();

    //Elements
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genterContainer = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesRadioButton = $("#hobbiesWrapper");
    private final SelenideElement uploadPicture = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement countrySelect = $("#react-select-3-input");
    private final SelenideElement citySelect = $("#react-select-4-input");
    private final SelenideElement submitButton = $("#submit");
    private final SelenideElement tableModalResponsive = $("#example-modal-sizes-title-lg");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");


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
        dateOfBirthInput.click();
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
    public RegistrationPage checkVisibleTableResponsive () {
        tableModalResponsive
                .shouldBe(visible)
                .shouldHave(text("Thanks for submitting the form"));

        return this;
    }
    public void checkNotVisibleTableResponsive () {
        tableModalResponsive
                .shouldNotBe(visible);

    }
    public RegistrationPage checkResult (String label, String expectedValue) {
        modalResultComponent.checkTableResponsive(label, expectedValue);

        return this;
    }
    public RegistrationPage checkEmptyResult (String label) {
        modalResultComponent.checkEmptyTableResponsive(label);

        return this;
    }
    public RegistrationPage removeBanner () {
        RemoveBannerUtils.removeAdElements();

        return this;
    }
}
