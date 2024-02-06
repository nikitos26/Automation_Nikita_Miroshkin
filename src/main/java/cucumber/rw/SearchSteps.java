package cucumber.rw;

import base.BaseTestSelenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import projects.rw.pages.HomePage;
import projects.rw.pages.SearchPage;

public class SearchSteps extends BaseTestSelenide {

    @Given("Open home page")
    public void openHomePage() {
        get(HomePage.class);
    }

    @When("I enter from {string}")
    public void enterFrom(String city) {
        get(HomePage.class).enterFrom(city);
    }

    @And("I enter to {string}")
    public void enterTo(String city) {
        get(HomePage.class).enterTo(city);
    }

    @And("Click on search")
    public void clickOnSearch() {
        get(HomePage.class).clickSearch();
    }

    @Then("I check result page, direction from {string} to {string}")
    public void checkedResults(String from, String to) {
        get(SearchPage.class).verifyDirectionInTitle(from, to);
    }
}
