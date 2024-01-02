package utils.testing;

import org.testng.ITestContext;
import org.testng.ITestListener;

import static utils.properties.PropertyReader.setUpProperty;

public class Listener implements ITestListener {

    @Override
    public void onStart(ITestContext context) {
          if (System.getProperties().containsKey("config")) {
            setUpProperty(System.getProperty("config"));
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test finished " + context.getSuite().getName());
    }
}
