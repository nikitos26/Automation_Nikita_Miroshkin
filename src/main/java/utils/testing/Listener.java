package utils.testing;

import lombok.extern.log4j.Log4j;
import org.testng.ITestContext;
import org.testng.ITestListener;

import static utils.properties.PropertyReader.setUpProperty;

@Log4j
public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
          if (System.getProperties().containsKey("config")) {
            setUpProperty(System.getProperty("config"));
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        log.warn("Test finished " + context.getSuite().getName());
    }
}
