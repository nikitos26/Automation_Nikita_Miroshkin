package base;

import driver.DriverCreation;
import driver.DriverTypes;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import utils.testing.InvokedListener;
import utils.testing.Listener;

import static driver.SimpleWebDriver.getDriver;
import static utils.properties.PropertyReader.getProperties;


@Listeners({Listener.class, InvokedListener.class})
public abstract class BaseTest {

    @BeforeTest
    protected void setUp() {
        DriverCreation.createDriver(System.getProperties().containsKey("config")
                ? DriverTypes.valueOf(getProperties().getProperty("browser").toUpperCase())
                : DriverTypes.CHROME);
    }

    @AfterTest(alwaysRun = true)
    protected void tearDown() {
        DriverCreation.quitDriver();
    }

    protected void removeCookiesAndSetUrl(String url) {
        getDriver().navigate().refresh();
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().to(url);
    }

}
