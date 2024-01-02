package lesson_10;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projects.saucedemo.pages.LoginPage;
import projects.saucedemo.pages.ProductPage;

public class Lesson_10 extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage();
        loginPage.open();
    }

    @Test(priority = 1, description = "Successful user login")
    public void testProperties() {
        loginPage.verifyPage();
        loginPage.enterUsername();
        loginPage.enterPassword();
        loginPage.clickLogin();
        ProductPage productPage = new ProductPage();
        productPage.verifyPage();
    }
}
