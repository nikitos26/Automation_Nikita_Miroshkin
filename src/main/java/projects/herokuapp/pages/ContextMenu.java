package projects.herokuapp.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class ContextMenu extends BasePage {
    private final By header = By.tagName("h3");
    private final By spot = By.id("hot-spot");

    public void verifyTitleText() {
        wait.until(textToBe(header, "Context Menu"));
    }

    public void waitAlertAppear() {
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void verifyTextAlert() {
        Assert.assertEquals(driver.switchTo().alert().getText(), "You selected a context menu",
                "Wrong alert message");
    }

    public void closeAlert() {
        driver.switchTo().alert().accept();
    }

    public void rightClickOnArea() {
        actions.contextClick(driver.findElement(spot)).perform();
    }

}
