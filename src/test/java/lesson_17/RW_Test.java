package lesson_17;

import base.BaseTestSelenide;
import entities.rw.Directions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projects.rw.pages.ContactsPage;
import projects.rw.pages.HomePage;
import projects.rw.pages.SearchPage;
import utils.provider.DataProviderClass;

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

//    @Test(priority = 2, description = "Search tickets", dataProvider = "Directions for travel.",
//            dataProviderClass = DataProviderClass.class)
//    public void searchTicketsByRoutes(Directions directions) {
//        get(HomePage.class)
//                .enterFrom(directions)
//                .enterTo(directions)
//                .clickRoutes()
//                .clickSearch();
//        get(SearchPage.class)
//                .verifyDirectionInTitle(directions);
//    }

    @Test(priority = 3, description = "Search tickets by station")
    public void searchTicketsByStations() {
        get(HomePage.class)
                .clickStations()
                .enterStation("Минск-Пассажирский")
                .clickSearch()
                .clickProceedButton()
                .clickProceedButton()
                .clickProceedButton()
                .clickProceedButton();
        get(SearchPage.class)
                .verifyDirectionInTitle("Минск-Пассажирский");
    }

}
