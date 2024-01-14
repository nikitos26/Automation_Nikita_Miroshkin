package lesson_13;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import patterns.chainOfInvocations.saucedemo.CatalogPage;
import projects.saucedemo.pages.CartPage;
import utils.provider.DataProviderClass;

public class CatalogChainOfInvocationsTest {
    private CatalogPage catalogPage;
    private CartPage cartPage;
    private Integer productIndex = 0;

    @BeforeClass
    public void precondition() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
    }

    @Test(priority = 1, description = "Add products in cart by index", invocationCount = 3,
            dependsOnMethods = "successfulLogin")
    public void addProductsToCart() {
        catalogPage.verifyPage()
                .clickAddToCard(productIndex++);
    }

    @Test(priority = 2, dependsOnMethods = "addProductsToCart", description = "Check added products in cart")
    public void checkProductInCart() {
        catalogPage.clickOnCartIcon();
        Assert.assertEquals(cartPage.getAmountItemsInCart(), productIndex, "Wrong amount items in cart");
    }

    @Test(priority = 3, dependsOnMethods = "checkProductInCart", invocationCount = 3,
            description = "Remove products from cart by index")
    public void removeProductInCart() {
        productIndex--;
        cartPage.removeItemBtnFromCartByIndex(productIndex);
        Assert.assertEquals(cartPage.getAmountItemsInCart(), productIndex, "Cart has products after remove");
    }


    @Test(priority = 4, description = "Add products to cart by name", dataProvider = "product names",
            dataProviderClass = DataProviderClass.class)
    public void addProductsInCardByName(String productName) {
        catalogPage.setPage()
                .clickOnItemByName(productName);
    }

    @Test(priority = 5, dependsOnMethods = "addProductsInCardByName", description = "Verify amount added products in cart")
    public void verifyAddedProductsInCart() {
        catalogPage.clickOnCartIcon();
        Assert.assertEquals(cartPage.getAmountItemsInCart(), 3, "Wrong amount added product by name");
    }

    @Test(priority = 6, dependsOnMethods = "verifyAddedProductsInCart",
            description = "Remove products from cart by names", dataProvider = "product names",
            dataProviderClass = DataProviderClass.class)
    public void removeItemsFromCartByName(String productName) {
        cartPage.clickRemoveBntByNameProduct(productName);
    }

    @Test(priority = 7, dependsOnMethods = "removeItemsFromCartByName",
            description = "Verify amount products after removing")
    public void verifyAmountProductsAfterRemoving() {
        Assert.assertEquals(cartPage.getAmountItemsInCart(), 0, "Wrong products cart after removing");
    }
}
