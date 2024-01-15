package patterns.pageFactory.saucedemo;

import base.BasePage;
import driver.DriverCreation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

import static driver.DriverCreation.getDriver;

public class CatalogPage extends BasePage {
    @FindBy(className = "app_logo")
    WebElement header;

    @FindBy(className = "inventory_item")
    List<WebElement> productList;

    @FindBy(className = "shopping_cart_link")
    WebElement cardIcon;

    @FindBy(css = ".inventory_item_name")
    List<WebElement> itemName;

    private final By addToCard = By.tagName("button");
    private final String pageUrl = "https://www.saucedemo.com/inventory.html";

    public CatalogPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public void verifyPage() {
        Assert.assertEquals(header.getText(), "Swag Labs", "Wrong header name.");
        Assert.assertFalse(productList.isEmpty(), "Product list is empty.");
        Assert.assertEquals(DriverCreation.getWebDriver().getCurrentUrl(), "https://www.saucedemo.com/inventory.html",
                "Wrong catalog url.");
    }

    public void clickAddToCard(Integer index) {
        click(productList.get(index).findElement(addToCard));
    }

    public void clickOnCartIcon() {
        click(cardIcon);
    }

    public String getItemNameByIndex(Integer index) {
        return itemName.get(index).getText();
    }

    public void clickOnItemByName(String itemName) {
        By itemBtnByName = By.xpath("//div[contains(text(),'" + itemName + "')]//ancestor::div[@class='inventory_item_description']/descendant::button");
        driver.findElement(itemBtnByName).click();
    }

    public void setPage() {
        driver.get(pageUrl);
    }
}
