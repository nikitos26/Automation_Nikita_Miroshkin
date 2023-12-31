package lesson_9;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import projects.saucedemo.pages.LoginPage;
import projects.saucedemo.pages.ProductPage;
import utils.provider.DataProviderClass;

public class LoginTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeMethod
    public void precondition() {
        loginPage = new LoginPage();
        loginPage.open(loginPage.getPageUrl());
    }

    @Test(priority = 1, description = "Login with wrong password")
    @Parameters("userName")
    public void loginWithWrongPass(@Optional("Test") String userName) {
        loginPage.verifyPage();
        loginPage.enterUsername(userName);
        loginPage.enterPassword("TestTest");
        loginPage.clickLogin();
        loginPage.checkWrongLoginTest();
    }

    @Parameters({"password"})
    @Test(priority = 2, dataProvider = "wrong user data", dataProviderClass = DataProviderClass.class,
            description = "Login with wring user names")
    public void loginWithWrongNames(String name, String password) {
        loginPage.verifyPage();
        loginPage.enterUsername(name);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        loginPage.checkWrongLoginTest();
    }

    @Test(priority = 3, description = "Login with long name")
    public void loginWithLongName(@Optional("TestTestTestTestTestTEstTestTestTEstTest") String name) {
        loginPage.verifyPage();
        loginPage.enterUsername(name);
        loginPage.enterPassword(loginPage.getPassword());
        loginPage.clickLogin();
        loginPage.checkWrongLoginTest();
    }

    @Test(priority = 4, description = "Successful user login")
    @Parameters({"username", "password"})
    public void successfulLogin(String userName, String password) {
        loginPage.verifyPage();
        loginPage.enterUsername(userName);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        ProductPage productPage = new ProductPage();
        productPage.verifyPage();
    }
}
