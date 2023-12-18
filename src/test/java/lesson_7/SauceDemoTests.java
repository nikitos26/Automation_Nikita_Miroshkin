package lesson_7;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import projects.saucedemo.pages.*;

import java.util.ArrayList;
import java.util.List;

public class SauceDemoTests extends BaseTest {
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CheckoutPage checkoutPage;
    private ProofOfPurchase proofOfPurchase;
    private CompletePurchase completePurchase;
    private List<String> productNameInCatalog = new ArrayList<>();
    private List<String> productNameInCart = new ArrayList<>();

    @BeforeTest
    public void precondition() {
        loginPage = new LoginPage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        proofOfPurchase = new ProofOfPurchase();
        completePurchase = new CompletePurchase();
        loginPage.open(loginPage.getPageUrl());
    }

    @Test(priority = 1)
    public void loginWithEmptyFields() {
        loginPage.verifyPage();
        loginPage.clickLogin();
        loginPage.checkErrorsText();
    }

    @Test(priority = 2)
    public void loginWithLongUserName() {
        loginPage.verifyPage();
        loginPage.enterUsername("TestTestTestTestTestTestTestTestTestTestTestTestTestTestTestTest");
        loginPage.enterPassword(loginPage.getPassword());
        loginPage.clickLogin();
        loginPage.checkWrongLoginTest();
    }


    @Test(priority = 3)
    public void successfulLoginTest() {
        loginPage.verifyPage();
        loginPage.enterUsername(loginPage.getUserNames().get(0));
        loginPage.enterPassword(loginPage.getPassword());
        loginPage.clickLogin();
        productPage.verifyPage();
    }

    @Test(priority = 4)
    public void addProductsToCartTest() {
        productPage.verifyPage();
        productNameInCatalog.add(productPage.getItemNameByIndex(0));
        productNameInCatalog.add(productPage.getItemNameByIndex(1));
        productPage.clickAddToCard(1);
        productPage.clickAddToCard(0);
    }

    @Test(priority = 5)
    public void checkItemsInCart() {
        productPage.clickOnCartIcon();
        cartPage.verifyPage();
        Assert.assertEquals(cartPage.getAmountItemsInCart(), 2);
        productNameInCart.add(cartPage.getItemNameByIndex(0));
        productNameInCart.add(cartPage.getItemNameByIndex(1));
        productNameInCatalog.forEach(product -> Assert.assertTrue(productNameInCart.contains(product),
                "Product " + product + " doesnt exists in Cart!"));
    }

    @Test(priority = 6)
    public void removeItemFromCart() {
        cartPage.removeItemBtnFromCartByIndex(0);
        Assert.assertEquals(cartPage.getAmountItemsInCart(), 1);
    }

    @Test(priority = 7)
    public void enterUserData() {
        cartPage.clickOnCheckoutBtn();
        checkoutPage.verifyPage();
        checkoutPage.enterFirstName("testFirstName");
        checkoutPage.enterLastName("testLastName");
        checkoutPage.enterPostCode("12345");
        checkoutPage.clickContinueBtn();
    }

    @Test(priority = 8)
    public void makePurchase() {
        proofOfPurchase.verifyPage();
        Assert.assertEquals(proofOfPurchase.getAmountItemInCart(), 1);
        proofOfPurchase.clickFinishBtn();
    }

    @Test(priority = 9)
    public void completePurchase() {
        completePurchase.verifyPage();
    }
}
