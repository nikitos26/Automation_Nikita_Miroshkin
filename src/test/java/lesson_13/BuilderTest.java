package lesson_13;

import base.BaseTest;
import entities.sausedemo.User;
import entities.sausedemo.UserBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import patterns.valueObject.sausedemo.LoginPage;
import projects.saucedemo.pages.ProductPage;
import utils.provider.DataProviderClass;

import static utils.properties.PropertyReader.getProperties;

public class BuilderTest extends BaseTest {
    private LoginPage loginPage;

    @BeforeTest
    public void precondition() {
        loginPage = new LoginPage();
    }

    @BeforeMethod
    public void setUpPage() {
        loginPage.open(loginPage.getPageUrl());
    }

    @Test(priority = 1, dataProvider = "get wrong user data from builder",
            dataProviderClass = DataProviderClass.class,
            description = "Login with wrong password")
    public void loginWithWrongPass(UserBuilder userBuilder) {
        loginPage.verifyPage()
                .login(userBuilder)
                .clickLogin()
                .checkWrongLoginTest();
    }

    @Test(priority = 2, description = "Successful user login")
    public void successfulLogin() {
        loginPage.verifyPage()
                .login(new User() {{
                    setUserName(getProperties().getProperty("username"));
                    setPassword(getProperties().getProperty("password"));
                }})
                .clickLogin();
        ProductPage productPage = new ProductPage();
        productPage.verifyPage();
    }
}
