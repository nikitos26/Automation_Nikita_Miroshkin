package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SimpleWebDriver {
    private static WebDriver webDriver;

    {
        if (webDriver == null){
            webDriver = new ChromeDriver(chromeOptions());
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        }
    }

    public static WebDriver getDriver() {
        return webDriver;
    }

    public static void setDriver(String url){
        if (webDriver == null) {
            webDriver = new ChromeDriver(chromeOptions());
            webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
            webDriver.get(url);
        }
    }

    public static ChromeOptions chromeOptions(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        return options;
    }

    public static void quit(){
        webDriver.quit();
    }


}
