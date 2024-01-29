package patterns.valueObject.sausedemo;

import base.BasePage;
import driver.DriverCreation;
import org.openqa.selenium.By;
import org.testng.Assert;

public class ProductPage extends BasePage {
    private final String pageUrl = "https://www.saucedemo.com/inventory.html";
    private final By header = By.className("app_logo");
    private final By productList = By.className("inventory_item");
    private final By addToCard = By.tagName("button");
    private final By cardIcon = By.className("shopping_cart_link");
    private final By itemName = By.cssSelector(".inventory_item_name");

    public ProductPage verifyPage() {
        Assert.assertEquals(DriverCreation.getDriver().findElement(header).getText(), "Swag Labs",
                "Wrong header name.");
        Assert.assertFalse(DriverCreation.getDriver().findElements(productList).isEmpty(), "Product list is empty.");
        Assert.assertEquals(DriverCreation.getDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "Wrong catalog url.");
        return this;
    }

    public ProductPage clickAddToCard(Integer index) {
        click(DriverCreation.getDriver().findElements(productList).get(index).findElement(addToCard));
        return this;
    }

    public ProductPage clickOnCartIcon() {
        click(cardIcon);
        return this;
    }

    public String getItemNameByIndex(Integer index) {
        return driver.findElements(itemName).get(index).getText();
    }

    public Integer getProductSize() {
        return getElementsCount(itemName);
    }

    public ProductPage clickOnItemByName(String itemName) {
        By itemBtnByName = By.xpath("//div[contains(text(),'" + itemName + "')]//ancestor::div[@class='inventory_item_description']/descendant::button");
        driver.findElement(itemBtnByName).click();
        return this;
    }

    public ProductPage setPage() {
        driver.get(pageUrl);
        return this;
    }
}