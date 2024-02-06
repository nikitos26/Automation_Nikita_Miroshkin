package utils.testing;

import org.testng.ITestContext;
import org.testng.ITestListener;

import static utils.properties.PropertyReader.getProperties;
import static utils.properties.PropertyReader.setUpProperty;
import static com.codeborne.selenide.Configuration.*;

public class SelenideListener implements ITestListener {
    @Override
    public void onStart(ITestContext context) {
        if (System.getProperties().containsKey("config")) {
            setUpProperty(System.getProperty("config"));
            setUpSelenideConfiguration();
        }
    }

    public void setUpSelenideConfiguration() {
        baseUrl = getProperties().getProperty("baseUrl");
        headless = Boolean.parseBoolean(getProperties().getProperty("headless"));
        timeout = Long.parseLong(getProperties().getProperty("timeout"));
    }
}
