package lesson_4;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class GoogleTest {
    WebDriver driver = new ChromeDriver();

    @BeforeTest
    public void setUp() {
        if (driver == null) {
            driver = new ChromeDriver();
        }
        driver.get("http://www.google.com");
    }

    public void closeCookiePopup() {
        try {
            WebElement closeCookiePopup = driver.findElement(By.cssSelector("[id=\"W0wltc\"]"));
            closeCookiePopup.click();
        } catch (NoSuchElementException e) {
            System.out.println("Element closeCookiePopup not found.");
        }
    }

    public void resetUrl() {
        System.out.println(driver.getCurrentUrl());
        if (!driver.getCurrentUrl().contains("http://www.google.com")) {
            driver.get("http://www.google.com");
        }
    }

    private void inputText(String text) {
        WebElement searchField = driver.findElement(By.name("q"));
        searchField.sendKeys(text);
        searchField.submit();
    }

    @Test
    public void printTextInSearchField() {
        resetUrl();
        closeCookiePopup();
        inputText("Привет, мир");
        assertTrue(driver.getTitle().startsWith("Привет, мир"));
    }

    @Test
    public void emptySearch() {
        resetUrl();
        closeCookiePopup();
        inputText("-DskipTests=true");
        WebElement noResultsMessage = driver.findElement(By.cssSelector(".card-section"));
        assertTrue(noResultsMessage.getText().contains("По запросу -DskipTests=true ничего не найдено."),
                "Ожидаемый текст не найден.");
    }

    @AfterTest
    public void close_driver() {
        driver.quit();
    }
}

