package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.data.RandomizTestData;
import tests.pages.TexBoxPage;

import static tests.data.TestData.*;

public class TextBoxPositiveTests {
    TexBoxPage texBoxPage = new TexBoxPage();
    private RandomizTestData randomData;

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
    @BeforeEach
    public void setRandomDataUp() {
        randomData = new RandomizTestData();
    }
    @Test
    void successRandomRequiredFormTests() {
        texBoxPage
                .openPege()
                .typeUserName(randomData.firstRandomName+" "+randomData.lastRandomName)
                .typUserEmail(randomData.userRandomEmail)
                .typCurrentAddress(randomData.currentAddressRandom)
                .typPermanentAddress(randomData.permanentAddressRandom)
                .submitForm()

                .checkField("name", randomData.firstRandomName+" "+randomData.lastRandomName)
                .checkField("email", randomData.userRandomEmail)
                .checkField("currentAddress", randomData.currentAddressRandom)
                .checkField("permanentAddress", randomData.permanentAddressRandom);
    }
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }

}
