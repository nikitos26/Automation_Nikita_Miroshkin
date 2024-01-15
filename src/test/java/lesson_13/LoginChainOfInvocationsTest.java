package lesson_13;

import base.BaseTest;
import org.testng.annotations.*;
import patterns.chainOfInvocations.saucedemo.LoginPage;
import projects.saucedemo.pages.ProductPage;
import utils.provider.DataProviderClass;

public class LoginChainOfInvocationsTest extends BaseTest {
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
    @Parameters("username")
    public void loginWithWrongPass(@Optional("Test") String userName) {
        loginPage.verifyPage()
                .enterUsername(userName)
                .enterPassword("TestTest")
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Parameters({"password"})
    @Test(priority = 2, dataProvider = "wrong user data", dataProviderClass = DataProviderClass.class,
            description = "Login with wring user names")
    public void loginWithWrongNames(String name, String password) {
        loginPage.verifyPage()
                .enterUsername(name)
                .enterPassword(password)
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Test(priority = 3, description = "Login with long name")
    public void loginWithLongName(@Optional("TestTestTestTestTestTEstTestTestTEstTest") String name) {
        loginPage.verifyPage()
                .enterUsername(name)
                .enterPassword(loginPage.getPassword())
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Test(priority = 4, description = "Successful user login")
    public void successfulLogin() {
        loginPage.verifyPage()
                .enterUsername()
                .enterPassword()
                .clickLogin();
        ProductPage productPage = new ProductPage();
        productPage.verifyPage();
    }
}

