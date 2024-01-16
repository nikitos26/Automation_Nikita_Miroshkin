package lesson_14;

import base.BaseTest;
import entities.sausedemo.Password;
import entities.sausedemo.User;
import entities.sausedemo.UserBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import patterns.valueObject.sausedemo.LoginPage;
import projects.saucedemo.pages.ProductPage;
import utils.provider.DataProviderClass;

public class LoginTest extends BaseTest {
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
                .enterUsername(new User() {{
                    setUserName("TestTest");
                }})
                .enterPassword(new Password.PasswordBuilder().password("TestTest").build())
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Test(priority = 2, dataProvider = "wrong user data from builder", dataProviderClass = DataProviderClass.class,
            description = "Login with wring user names")
    public void loginWithWrongNames(UserBuilder userBuilder) {
        loginPage.verifyPage()
                .login(userBuilder)
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Test(priority = 3, description = "Login with long name")
    public void loginWithLongName() {
        loginPage.verifyPage()
                .enterUsername(new User() {{
                    setUserName("TestTestTestTestTestTEstTestTestTEstTest");
                }})
                .enterPassword(loginPage.getPassword())
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Test(priority = 4, description = "Successful user login from builder",
            dataProvider = "Successful user login from builder", dataProviderClass = DataProviderClass.class)
    public void successfulLogin(UserBuilder userBuilder) {
        loginPage.verifyPage()
                .login(userBuilder)
                .clickLogin();
        ProductPage productPage = new ProductPage();
        productPage.verifyPage();
    }
}
