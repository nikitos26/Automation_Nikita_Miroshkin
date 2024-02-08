package lesson_18;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/feature/rabota_by.feature"},
        plugin = {
                "json:target/rabota_by/cucumber.json",
                "html:target/rabota_by/cucumber-pretty"
        },
        glue = {"cucumber/rabota_by"}
)
public class RabotaByTest extends AbstractTestNGCucumberTests {
        @Override
        @DataProvider
        public Object[][] scenarios() {
                return super.scenarios();
        }
}
