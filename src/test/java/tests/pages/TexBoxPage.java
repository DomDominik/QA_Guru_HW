package tests.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byId;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TexBoxPage {

    //Elements
    private SelenideElement  userNameInput = $("#userName");
    private SelenideElement  userEmailInput = $("#userEmail");
    private SelenideElement  currentAddressInput = $("#currentAddress");
    private SelenideElement  permanentAddressInput = $("#permanentAddress");
    private SelenideElement  outputResoults = $("#output");

    private SelenideElement  submitButton = $("#submit");



    //Actions
    public TexBoxPage openPege () {
        open("text-box");

        return  this;
    }
    public TexBoxPage typeUserName (String value) {
        userNameInput.setValue(value);

        return  this;
    }
    public TexBoxPage typUserEmail (String value) {
        userEmailInput.setValue(value);

        return  this;
    }
    public TexBoxPage typCurrentAddress (String value) {
        currentAddressInput.setValue(value);

        return  this;
    }
    public TexBoxPage typPermanentAddress (String value) {
        permanentAddressInput.setValue(value);

        return  this;
    }
    public TexBoxPage submitForm () {
        submitButton.click();

        return  this;
    }
    public TexBoxPage checkField (String key, String value) {
        outputResoults.$(byId(key)).shouldHave(text(value));

        return  this;
    }
}
