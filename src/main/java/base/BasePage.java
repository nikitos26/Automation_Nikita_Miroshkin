package base;


import driver.DriverCreation;
import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;

@Log4j
public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected Actions actions;

    {
        driver = DriverCreation.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10), Duration.ofSeconds(1));
        actions = new Actions(driver);
    }

    protected void navigateTo(String url) {
        log.info("Navigate to :: " + url);
        driver.get(url);
    }

    protected void click(By by) {
        click(driver.findElement(by));
    }

    protected void click(WebElement element) {
        log.info("Click on element :: " + element);
        element.click();
    }

    protected void sendKeys(By by, CharSequence... charSequences) {
        sendKeys(driver.findElement(by), charSequences);
    }

    protected void sendKeys(WebElement element, CharSequence... charSequences) {
        log.info("Enter in :: " + element + ", next values :: " + Arrays.toString(charSequences));
        element.clear();
        element.sendKeys(charSequences);
    }

    protected Integer getElementsCount(By by) {
        return driver.findElements(by).size();
    }

    protected void waitUntil(Integer seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected void waitLocatorDisappear(By by) {
        log.info("Wait disappear :: " + by);
        click(driver.findElement(by));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    protected void waitLocatorDisappear(WebElement element) {
        log.info("Wait disappear :: " + element);
        wait.until(ExpectedConditions.invisibilityOf(element));
    }

    protected void waitLocatorAppear(WebElement element) {
        log.info("Wait appear :: " + element);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitLocatorAppear(By by) {
        log.info("Wait appear :: " + by);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    protected String get_text(WebElement element) {
        return element.getText();
    }

    protected String get_text(By by) {
        return driver.findElement(by).getText();
    }
}
