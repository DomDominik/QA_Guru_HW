package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.pages.TexBoxPage;

import static tests.data.RandomizTestData.*;
import static tests.data.TestData.*;

public class TextBoxPositiveTests {
    TexBoxPage texBoxPage = new TexBoxPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";
    }
    @Test
    void successRequiredFormTests() {
        texBoxPage
                .openPege()
                .typeUserName(firstName+" "+lastName)
                .typUserEmail(userEmail)
                .typCurrentAddress(currentAddress)
                .typPermanentAddress(permanentAddress)
                .submitForm()

                .checkField("name", firstName+" "+lastName)
                .checkField("email", userEmail)
                .checkField("currentAddress", currentAddress)
                .checkField("permanentAddress", permanentAddress);
    }
    @Test
    void successRandomRequiredFormTests() {
        texBoxPage
                .openPege()
                .typeUserName(firstRandomName+" "+lastRandomName)
                .typUserEmail(userRandomEmail)
                .typCurrentAddress(currentAddressRandom)
                .typPermanentAddress(permanentAddressRandom)
                .submitForm()

                .checkField("name", firstRandomName+" "+lastRandomName)
                .checkField("email", userRandomEmail)
                .checkField("currentAddress", currentAddressRandom)
                .checkField("permanentAddress", permanentAddressRandom);
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }

}
