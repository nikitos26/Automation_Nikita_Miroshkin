package lesson_17;

import base.BaseTestSelenide;
import entities.rw.Railway;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projects.rw.pages.ContactsPage;
import projects.rw.pages.HomePage;
import projects.rw.pages.SearchPage;
import utils.provider.DataProviderClass;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class RW_Test extends BaseTestSelenide {

    @BeforeMethod
    public void precondition() {
        get(HomePage.class)
                .openHomePage();
    }

    @Test(priority = 1, description = "Open contacts page")
    public void openContactsPage() {
        get(HomePage.class)
                .clickContacts();
        get(ContactsPage.class)
                .verifyPage();
    }

    @Test(priority = 2, description = "Search tickets", dataProvider = "Directions for travel.",
            dataProviderClass = DataProviderClass.class)
    public void searchTicketsByRoutes(Railway directions) {
        get(HomePage.class)
                .enterFrom(directions)
                .enterTo(directions)
                .clickRoutes()
                .clickSearch();
        get(SearchPage.class)
                .verifyDirectionInTitle(directions);
    }

    @Test(priority = 3, description = "Parse Data")
    public void parseTicketsData() {
        get(HomePage.class)
                .enterFrom("Минск")
                .enterTo("Борисов")
                .clickRoutes()
                .clickSearch();
        get(SearchPage.class)
                .verifyDirectionInTitle("Минск", "Борисов")
                .parseTicketsResult();
    }


    @Test(priority = 4, description = "Search tickets by station", dataProvider = "Railway stations.",
            dataProviderClass = DataProviderClass.class)
    public void searchTicketsByStations(Railway station) {
        get(HomePage.class)
                .clickStations()
                .enterStation(station)
                .clickSearch()
                .clickProceedButton()
                .clickProceedButton();
        open(getWebDriver().getCurrentUrl().replaceAll("http", "https"));
        ;
        get(SearchPage.class)
                .verifyStationInTitle(station);
    }

}
