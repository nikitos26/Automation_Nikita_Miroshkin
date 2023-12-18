package projects.saucedemo.pages;


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

    public void verifyPage() {
        Assert.assertEquals(DriverCreation.getWebDriver().findElement(header).getText(), "Swag Labs",
                "Wrong header name.");
        Assert.assertFalse(DriverCreation.getWebDriver().findElements(productList).isEmpty(), "Product list is empty.");
        Assert.assertEquals(DriverCreation.getWebDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "Wrong catalog url.");
    }

    public void clickAddToCard(Integer index) {
        click(DriverCreation.getWebDriver().findElements(productList).get(index).findElement(addToCard));
    }

    public void clickOnCartIcon() {
        click(cardIcon);
    }

    public String getItemNameByIndex(Integer index) {
        return driver.findElements(itemName).get(index).getText();
    }

    public Integer getProductSize() {
        return getElementsCount(itemName);
    }
}
