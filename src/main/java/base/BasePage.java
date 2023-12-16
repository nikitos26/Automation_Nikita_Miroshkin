package base;


import driver.DriverCreation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    {
        driver = DriverCreation.getWebDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1));
        actions = new Actions(driver);
    }

    protected void navigateTo(String url) {
        System.out.println("Navigate to :: " + url);
        driver.get(url);
    }

    protected void click(By by) {
        click(driver.findElement(by));
    }

    protected void click(WebElement element) {
        System.out.println("Click on element :: " + element);
        element.click();
    }

    protected void sendKeys(By by, CharSequence... charSequences) {
        sendKeys(driver.findElement(by), charSequences);
    }

    protected void sendKeys(WebElement element, CharSequence... charSequences) {
        System.out.println("Enter in :: " + element + ", next values :: " + Arrays.toString(charSequences));
        element.clear();
        element.sendKeys(charSequences);
    }

    protected Integer getElementsCount(By by) {
        return driver.findElements(by).size();
    }
}
