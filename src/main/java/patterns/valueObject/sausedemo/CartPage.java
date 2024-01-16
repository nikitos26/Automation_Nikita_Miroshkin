package patterns.valueObject.sausedemo;

import base.BasePage;
import base.BaseTest;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends BasePage {
    private final String pageUrl = "https://www.saucedemo.com/cart.html";
    private final By checkoutBtn = By.cssSelector(".checkout_button");
    private final By cartItem = By.className("cart_item");
    private final By removeBtn = By.cssSelector(" .cart_item_label .btn");
    private final By itemName = By.cssSelector(" .inventory_item_name");

    public CartPage verifyPage() {
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl, "Wrong page url.");
        Assert.assertTrue(driver.findElement(checkoutBtn).isDisplayed());
        return this;
    }

    public Integer getAmountItemsInCart() {
        return getElementsCount(cartItem);
    }

    public String getItemNameByIndex(Integer index) {
        return driver.findElements(cartItem).get(index).findElement(itemName).getText();
    }

    public CartPage removeItemBtnFromCartByIndex(Integer index) {
        click(driver.findElements(cartItem).get(index).findElement(removeBtn));
        return this;
    }

    public CartPage clickOnCheckoutBtn() {
        click(this.checkoutBtn);
        return this;
    }

    public CartPage clickRemoveBntByNameProduct(String productName) {
        By removeBtn = By.xpath("//div[contains(text(),'" + productName + "')]//ancestor::div[@class='cart_item_label']/descendant::button");
        click(driver.findElement(removeBtn));
        return this;
    }
}
