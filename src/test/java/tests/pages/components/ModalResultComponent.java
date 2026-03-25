package tests.pages.components;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static tests.data.Variables.*;

public class ModalResultComponent {
    public void tableFullFieldResult () {
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName+" "+lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldHave(text(userEmail));
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genterWrapper));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldHave(text(dayOfBirth+" "+monthOfBirth+","+yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).parent().shouldHave(text(subjects));
        $(".table-responsive").$(byText("Hobbies")).parent().shouldHave(text(hobbiesSports+", "+hobbiesReading));
        $(".table-responsive").$(byText("Picture")).parent().shouldHave(text(nameOfFile));
        $(".table-responsive").$(byText("Address")).parent().shouldHave(text(currentAddress));
        $(".table-responsive").$(byText("State and City")).parent().shouldHave(text(country+" "+city));
    }
    public void tableRequiredFieldResult () {
        $(".table-responsive").$(byText("Student Name")).parent().shouldHave(text(firstName+" "+lastName));
        $(".table-responsive").$(byText("Student Email")).parent().shouldBe();
        $(".table-responsive").$(byText("Gender")).parent().shouldHave(text(genterWrapper));
        $(".table-responsive").$(byText("Mobile")).parent().shouldHave(text(userNumber));
        $(".table-responsive").$(byText("Date of Birth")).parent().shouldBe();
        $(".table-responsive").$(byText("Subjects")).parent().shouldBe();
        $(".table-responsive").$(byText("Hobbies")).parent().shouldBe();
        $(".table-responsive").$(byText("Picture")).parent().shouldBe();
        $(".table-responsive").$(byText("Address")).parent().shouldBe();
        $(".table-responsive").$(byText("State and City")).parent().shouldBe();
    }

}
