package base;

import driver.DriverCreation;
import driver.DriverTypes;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static driver.SimpleWebDriver.getDriver;


public abstract class BaseTest {

    @BeforeTest
    protected void setUp() {
        DriverCreation.createDriver(DriverTypes.CHROME);
    }

    @AfterTest
    protected void tearDown() {
        DriverCreation.quitDriver();
    }

    protected void refreshPageAndClearCookies(String url){
        getDriver().navigate().refresh();
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().to(url);
    }

}
