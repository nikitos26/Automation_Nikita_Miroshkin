package patterns.pageFactory.saucedemo;

import base.BasePage;
import driver.DriverCreation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static driver.DriverCreation.getDriver;
import static utils.properties.PropertyReader.getProperties;

public class LoginPage extends BasePage {
    @FindBy(className = "login_logo")
    WebElement header;

    @FindBy(id = "user-name")
    WebElement username;

    @FindBy(name = "password")
    WebElement password;

    @FindBy(css = "input[value='Login']")
    WebElement login;

    @FindBy(id = "login_credentials")
    WebElement loginCredentials;

    @FindBy(className = "login_password")
    WebElement passwordCredentials;

    @FindBy(tagName = "h3")
    WebElement errorBtn;

    public LoginPage() {
        PageFactory.initElements(getDriver(), this);
    }

    public String getPageUrl() {
        return "https://www.saucedemo.com";
    }

    public void open(String url) {
        getDriver().get(url);
    }

    public void open() {
        getDriver().get(getProperties().getProperty("url"));
    }

    public void verifyPage() {
        Assert.assertEquals(header.getText(), "Swag Labs", "Wrong header name.");
        Arrays.asList(username, password, login).forEach(el -> Assert.assertTrue(el.isDisplayed(),
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
        return Arrays.stream(loginCredentials.getText().split("\n"))
                .filter(value -> !value.contains("Accepted usernames are"))
                .collect(Collectors.toList());
    }

    public String getPassword() {
        return Arrays.stream(passwordCredentials.getText().split("\n"))
                .filter(value -> !value.contains("Password for all users"))
                .findFirst().orElse("");
    }

    public void checkErrorsText() {
        Assert.assertTrue(errorBtn.getText().contains("Username is required"),
                "Error not shown when logging in with empty fields.");
    }

    public void checkWrongLoginTest() {
        Assert.assertTrue(errorBtn.getText().contains("Username and password do not match any user in this service"),
                "Wrong text to wrong user name ot password");
    }
}
