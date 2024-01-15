package lesson_13;

import base.BaseTest;
import entities.sausedemo.User;
import org.testng.annotations.*;
import patterns.valueObject.sausedemo.LoginPage;
import projects.saucedemo.pages.ProductPage;
import static utils.properties.PropertyReader.getProperties;

public class ValueObjectTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeTest
    public void precondition() {
        loginPage = new LoginPage();
    }

    @BeforeMethod
    public void setUpPage() {
        loginPage.open(loginPage.getPageUrl());
    }

    @Test(priority = 1, description = "Login with wrong password")
    public void loginWithWrongPass() {
        loginPage.verifyPage()
                .login(new User(){{
                    setUserName(getProperties().getProperty("username"));
                    setPassword("Test");}})
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Test(priority = 2, description = "Successful user login")
    public void successfulLogin() {
        loginPage.verifyPage()
                .login(new User(){{
                    setUserName(getProperties().getProperty("username"));
                    setPassword(getProperties().getProperty("password"));}})
                .clickLogin();
        ProductPage productPage = new ProductPage();
        productPage.verifyPage();
    }
}

