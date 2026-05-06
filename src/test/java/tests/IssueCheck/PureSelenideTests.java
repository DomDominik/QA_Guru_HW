package tests.IssueCheck;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.openqa.selenium.By.linkText;

public class PureSelenideTests {

    @BeforeAll
    static void setupAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @DisplayName("Чистый Selenide")
    public void pureIssueTest() {
        open("https://github.com/");
        $( "qbsearch-input.search-input").click();
        $( "#query-builder-test").sendKeys("theonion/comcastifyjs");
        $( "#query-builder-test").submit();

        $(linkText("theonion/comcastifyjs")).click();
        $("#issues-repo-tab-count").click();
        $(withText("Data URI's are loading too fast")).should(exist);
    }
}
