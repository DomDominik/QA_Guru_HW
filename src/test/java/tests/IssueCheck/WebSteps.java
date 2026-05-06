package tests.IssueCheck;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class WebSteps {

    @Step("Открываем главную страницу")
    public void openMainPage(){
        open("https://github.com/");
    }
    @Step("Ищем репозиторий {repo}")
    public void searchRepo(String repo){
        $( "qbsearch-input.search-input").click();
        $( "#query-builder-test").sendKeys(repo);
        $( "#query-builder-test").submit();
    }
    @Step("Выбираем репозиторий {repo}")
    public void chooseRepo(String repo){
        $(linkText(repo)).click();
        $("#issues-repo-tab-count").click();
    }
    @Step("Проверяем {text}")
    public void checkIssue(String text){
        $(withText(text)).should(exist);
    }
    @Attachment(value = "Screenshot", type = "image/png", fileExtension = "png")
    public byte[] takeScreenshot() {
        return ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    }
}
