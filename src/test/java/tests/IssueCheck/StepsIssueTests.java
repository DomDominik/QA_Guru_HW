package tests.IssueCheck;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsIssueTests {
    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.browser ="Chrome";
        Configuration.browserVersion = "128";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }
    AllureReports allureReports = new AllureReports();
    WebSteps steps = new WebSteps();
    private final static String REPOSITORY = "theonion/comcastifyjs";
    private final static String TEXT = "Data URI's are loading too fast";
    private final static String ISSUES = "issues";

    @BeforeEach
    void setupAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @Test
    @DisplayName("Лямбда шаги через step")
    public void stepsLambdaTests() {
        step("Открываем главную страницу", () -> {
            open("https://github.com/");
        });
        step("Нажимаем на инпут поиска", () -> {
            $( "qbsearch-input.search-input").click();
        });
        step("Вводим: " + REPOSITORY, () -> {
            $( "#query-builder-test").sendKeys(REPOSITORY);
        });
        step("Жмем enter", () -> {
            $( "#query-builder-test").submit();
        });
        step("В полученном списке выбираем " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Переходим на вкладку " + ISSUES, () -> {
            $("#issues-repo-tab-count").click();
        });
        step("Проверяем что есть " + ISSUES + " " + TEXT, () -> {
            $(withText(TEXT)).should(exist);
        });
    }
    @Test
    @DisplayName("Шаги с аннотацией @Step")
    public void stepAnnotatedTest(){
        steps.openMainPage();
        steps.searchRepo(REPOSITORY);
        steps.chooseRepo(REPOSITORY);
        steps.checkIssue(TEXT);
        allureReports.takeScreenshot();
    }
}
