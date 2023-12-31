package projects.saucedemo.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.testng.Assert;

public class CartPage extends BasePage {
    private final String pageUrl = "https://www.saucedemo.com/cart.html";
    private final By checkoutBtn = By.cssSelector(".checkout_button");
    private final By cartItem = By.className("cart_item");
    private final By removeBtn = By.cssSelector(" .cart_item_label .btn");
    private final By itemName = By.cssSelector(" .inventory_item_name");

    public void verifyPage() {
        Assert.assertEquals(driver.getCurrentUrl(), pageUrl, "Wrong page url.");
        Assert.assertTrue(driver.findElement(checkoutBtn).isDisplayed());
    }

    public Integer getAmountItemsInCart() {
        return getElementsCount(cartItem);
    }

    public String getItemNameByIndex(Integer index) {
        return driver.findElements(cartItem).get(index).findElement(itemName).getText();
    }

    public void removeItemBtnFromCartByIndex(Integer index) {
        click(driver.findElements(cartItem).get(index).findElement(removeBtn));
    }

    public void clickOnCheckoutBtn() {
        click(this.checkoutBtn);
    }

    public void clickRemoveBntByNameProduct(String productName) {
        By removeBtn = By.xpath("//div[contains(text(),'" + productName + "')]//ancestor::div[@class='cart_item_label']/descendant::button");
        click(driver.findElement(removeBtn));
    }

}
