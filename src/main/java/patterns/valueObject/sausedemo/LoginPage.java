package patterns.valueObject.sausedemo;

import base.BasePage;
import driver.DriverCreation;
import entities.sausedemo.User;
import entities.sausedemo.UserBuilder;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;

import static utils.properties.PropertyReader.getProperties;

public class LoginPage extends BasePage {
    private final By header = By.className("login_logo");
    private final By username = By.id("user-name");
    private final By password = By.name("password");
    private final By login = By.cssSelector("input[value='Login']");
    private final By passwordCredentials = By.className("login_password");
    private final By errorBtn = By.tagName("h3");
    private final String pageUrl = "https://www.saucedemo.com";

    public String getPageUrl() {
        return pageUrl;
    }

    public LoginPage open(String url) {
        DriverCreation.getWebDriver().get(url);
        return this;
    }

    public LoginPage open() {
        DriverCreation.getWebDriver().get(getProperties().getProperty("url"));
        return this;
    }

    public LoginPage verifyPage() {
        Assert.assertEquals(driver.findElement(header).getText(), "Swag Labs", "Wrong header name.");
        Arrays.asList(username, password, login).forEach(el -> Assert.assertTrue(driver.findElement(el).isDisplayed(),
                "Element not displayed :: " + el));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Wrong header url.");
        return this;
    }

    public LoginPage enterUsername(String username) {
        sendKeys(this.username, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        sendKeys(this.password, password);
        return this;
    }

    public LoginPage clickLogin() {
        click(login);
        return this;
    }

    public String getPassword() {
        return Arrays.stream(driver.findElement(passwordCredentials).getText().split("\n"))
                .filter(value -> !value.contains("Password for all users"))
                .findFirst().orElse("");
    }

    public LoginPage checkWrongLoginTest() {
        Assert.assertTrue(driver.findElement(errorBtn).getText().contains("Username and password do not match any user in this service"),
                "Wrong text to wrong user name ot password");
        return this;
    }

    public LoginPage login(User user) {
        return enterUsername(user.getUserName()).enterPassword(user.getPassword());
    }

    public LoginPage login(UserBuilder userBuilder) {
        return enterUsername(userBuilder.getUserName()).enterPassword(userBuilder.getPassword());
    }
}