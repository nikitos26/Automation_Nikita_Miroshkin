package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverCreation {
    private static WebDriver webDriver;

    public static WebDriver getWebDriver() {
        return webDriver;
    }

    public static void quitDriver() {
        webDriver.quit();
    }

    public static void createDriver(DriverTypes type) {
        if (webDriver == null) {
            switch (type) {
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.addArguments("start-maximized");
                    webDriver = new ChromeDriver(options);
                    break;
                case IE:
                    webDriver = new EdgeDriver();
                    break;
                case FIREFOX:
                    webDriver = new FirefoxDriver();
                    break;
            }
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        }
    }

}
