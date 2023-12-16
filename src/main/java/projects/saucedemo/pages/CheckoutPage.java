package projects.saucedemo.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CheckoutPage extends BasePage {
    private final String pageUrl = "https://www.saucedemo.com/checkout-step-one.html";
    private final By title = By.className("title");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postCodeField = By.id("postal-code");
    private final By continueBtn = By.id("continue");

    public void verifyPage() {
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl, "Wrong Chekout Page Url");
        Assert.assertEquals(driver.findElement(title).getText(), "Checkout: Your Information",
                "Wrong page title");
        Assert.assertTrue(driver.findElement(continueBtn).isDisplayed(), "Button Continue doesnt appear");
    }

    public void enterFirstName(String firstName) {
        sendKeys(this.firstNameField, firstName);
    }

    public void enterLastName(String lastName) {
        sendKeys(this.lastNameField, lastName);
    }

    public void enterPostCode(String PostCode) {
        sendKeys(this.postCodeField, PostCode);
    }

    public void clickContinueBtn() {
        click(this.continueBtn);
    }

}
