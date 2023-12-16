package projects.saucedemo.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CompletePurchase extends BasePage {
    private final String pageUrl = "https://www.saucedemo.com/checkout-complete.html";
    private final By title = By.className("title");
    private final By headerMessage = By.className("complete-header");
    private final By backHomeBtn = By.name("back-to-products");

    public void verifyPage() {
        Assert.assertEquals(driver.getCurrentUrl(), this.pageUrl, "Wrong page url");
        Assert.assertEquals(driver.findElement(this.title).getText(), "Checkout: Complete!");
        Assert.assertEquals(driver.findElement(this.headerMessage).getText(), "Thank you for your order!",
                "Message in message header has wrong text");
        Assert.assertTrue(driver.findElement(backHomeBtn).isDisplayed(), "Back button doesnt appear");
    }
}
