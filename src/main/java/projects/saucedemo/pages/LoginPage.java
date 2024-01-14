package projects.saucedemo.pages;

import base.BasePage;
import driver.DriverCreation;
import org.openqa.selenium.By;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static utils.properties.PropertyReader.getProperties;

public class LoginPage extends BasePage {
    private final By header = By.className("login_logo");
    private final By username = By.id("user-name");
    private final By password = By.name("password");
    private final By login = By.cssSelector("input[value='Login']");
    private final By loginCredentials = By.id("login_credentials");
    private final By passwordCredentials = By.className("login_password");
    private final By errorBtn = By.tagName("h3");
    private final String pageUrl = "https://www.saucedemo.com";

    public String getPageUrl() {
        return pageUrl;
    }

    public void open(String url) {
        DriverCreation.getDriver().get(url);
    }

    public void open() {
        DriverCreation.getDriver().get(getProperties().getProperty("url"));
    }

    public void verifyPage() {
        Assert.assertEquals(driver.findElement(header).getText(), "Swag Labs", "Wrong header name.");
        Arrays.asList(username, password, login).forEach(el -> Assert.assertTrue(driver.findElement(el).isDisplayed(),
                "Element not displayed :: " + el));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/", "Wrong header url.");
    }

    public void enterUsername(String username) {
        sendKeys(this.username, username);
    }

    public void enterUsername() {
        sendKeys(this.username, getProperties().getProperty("username"));
    }

    public void enterPassword(String password) {
        sendKeys(this.password, password);
    }

    public void enterPassword() {
        sendKeys(this.password, getProperties().getProperty("password"));
    }

    public void clickLogin() {
        click(login);
    }

    public List<String> getUserNames() {
        return Arrays.stream(driver.findElement(loginCredentials).getText().split("\n"))
                .filter(value -> !value.contains("Accepted usernames are"))
                .collect(Collectors.toList());
    }

    public String getPassword() {
        return Arrays.stream(driver.findElement(passwordCredentials).getText().split("\n"))
                .filter(value -> !value.contains("Password for all users"))
                .findFirst().orElse("");
    }

    public void checkErrorsText() {
        Assert.assertTrue(driver.findElement(errorBtn).getText().contains("Username is required"),
                "Error not shown when logging in with empty fields.");
    }

    public void checkWrongLoginTest() {
        Assert.assertTrue(driver.findElement(errorBtn).getText().contains("Username and password do not match any user in this service"),
                "Wrong text to wrong user name ot password");
    }
}
