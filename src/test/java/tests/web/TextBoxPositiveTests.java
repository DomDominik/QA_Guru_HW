package tests.web;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tests.pages.TexBoxPage;

import static tests.data.Variables.*;

public class TextBoxPositiveTests {
    TexBoxPage texBoxPage = new TexBoxPage();

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser = "chrome";
        Configuration.baseUrl = "https://demoqa.com/";
    }
    @Test
    void successRequiredFormTests_dsl() {
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
    @AfterAll
    static void teaDown() {
        Selenide.closeWebDriver();
    }

}
