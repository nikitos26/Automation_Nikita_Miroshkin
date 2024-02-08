package lesson_18;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/feature/rw.feature"},
        plugin = {
                "json:target/cucumber.json",
                "html:target/rw/cucumber-pretty"
        },
        glue = {"cucumber/rw"}
)
public class RunRwCucumberTest extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
