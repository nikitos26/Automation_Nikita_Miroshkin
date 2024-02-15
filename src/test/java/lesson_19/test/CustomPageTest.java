package lesson_19.test;

import base.BaseTestSelenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import projects.myOwnPage.TestingPage;

import static com.codeborne.selenide.Selenide.open;

public class CustomPageTest extends BaseTestSelenide {

    @BeforeTest
    public void precondition() {
        open("file:////Users/nikitamiroskin/projects/Automation_Nikita_Miroshkin/src/test/java/lesson_19/files/test_page.html");

    }

    @Test(priority = 1, description = "Printing first row in table")
    public void printingFirstRowInTable() {
        get(TestingPage.class).printFirstRow();
    }

    @Test(priority = 2, description = "Input test text in field")
    public void inputTextInField() {
        get(TestingPage.class).inputText();
    }

    @Test(priority = 3, description = "Click on checkbox")
    public void clickOnCheckbox() {
        get(TestingPage.class).clickOnCheckBox();
    }

    @Test(priority = 4, description = "Chose element in list")
    public void choseElementInList() {
        get(TestingPage.class).selectElementInList();
    }

    @Test(priority = 5, description = "Click on button")
    public void clickOnButton() {
        get(TestingPage.class).clickOnButton();
    }

    @Test(priority = 6, description = "Verify image")
    public void verifyImage() {
        get(TestingPage.class).verifyImage();
    }

    @Test(priority = 7, description = "Verify tag text")
    public void verifyTagText() {
        Assert.assertTrue(get(TestingPage.class).getTag().getText().contains("Tag text."));
    }

    @Test(priority = 8, description = "Click on link")
    public void clickOnLink() {
        get(TestingPage.class).clickOnLink();
        Assert.assertTrue(WebDriverRunner.getWebDriver().getCurrentUrl().equals("https://www.onliner.by/"));
    }
}
