package cucumber.rabota_by;

import base.BaseTestSelenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import projects.rabota_by.pages.HomePage;
import projects.rabota_by.pages.SearchPage;

public class SearchJobSteps extends BaseTestSelenide {
    @Given("Open main page")
    public void openHomePage() {
        get(HomePage.class);
    }

    @When("Enter job - {string}")
    public void enterJob(String job) {
        get(HomePage.class).inputJob(job);
    }

    @And("Click on search button")
    public void clickSearchButton() {get(HomePage.class).clickSearchButton();}

    @Then("Verify result greatest then 0")
    public void verifyResult() {
        Assert.assertTrue(get(SearchPage.class).getResult().size() > 0);
    }

    @Then("Verify job in title {string}")
    public void verifyJobInTitle(String job) {
        Assert.assertTrue(get(SearchPage.class).getTitleText().contains(job));
    }
}
