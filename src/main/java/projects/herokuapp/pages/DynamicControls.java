package projects.herokuapp.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class DynamicControls extends BasePage {
    private final By header = By.tagName("h4");
    private final By checkbox = By.id("checkbox");
    private final By removeBtn = By.xpath("//*[text()='Remove']");
    private final By message = By.id("message");
    private final By input = By.cssSelector("[type='text']");
    private final By inputEnableBtn = By.xpath("*//form[@id='input-example']/button");
    private final By inputMessage = By.xpath("*//form[@id='input-example']/p");

    public void verifyPage() {
        Assert.assertEquals(driver.findElement(header).getText(), "Dynamic Controls",
                "Wrong page header");
    }

    public void clickRemoveBtn() {
        click(driver.findElement(removeBtn));
    }

    public void verifyInputStatus(Boolean isDisable) {
        wait.until(ExpectedConditions.attributeToBe(driver.findElement(input), "disabled",
                (isDisable ? "true" : "")));
    }

    public void verifyTextMessageCheckbox() {
        wait.until(ExpectedConditions.textToBe(message, "It's gone!"));
    }

    public void checkboxDisappear() {
        waitLocatorDisappear(driver.findElement(checkbox));
    }

    public void clickEnableBtn(){
        click(driver.findElement(inputEnableBtn));
    }

    public void verifyInputTextMessage() {
        wait.until(ExpectedConditions.textToBe(inputMessage, "It's enabled!"));
    }
}
