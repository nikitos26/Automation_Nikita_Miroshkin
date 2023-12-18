package projects.saucedemo.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProofOfPurchase extends BasePage {
    private final String pageUrl = "https://www.saucedemo.com/checkout-step-two.html";
    private final By finishBtn = By.name("finish");
    private final By summeryInfo = By.className("summary_info");
    private final By cartItem = By.className("cart_item");

    public void verifyPage() {
        Assert.assertEquals(driver.getCurrentUrl(), this.pageUrl);
        Assert.assertTrue(driver.findElement(finishBtn).isDisplayed());
        Assert.assertTrue(driver.findElement(summeryInfo).isDisplayed());
    }

    public void clickFinishBtn() {
        click(this.finishBtn);
    }

    public Integer getAmountItemInCart() {
        return getElementsCount(this.cartItem);
    }

}
