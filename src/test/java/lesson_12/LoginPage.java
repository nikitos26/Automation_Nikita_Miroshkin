package lesson_12;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import projects.herokuapp.pages.HomePage;
import projects.herokuapp.pages.SortableDataTables;

import static projects.herokuapp.pages.HomePageItems.SORTABLE_DATA_TABLES;

public class LoginPage extends BaseTest {
    HomePage homePage;
    SortableDataTables sortableDataTables;

    @BeforeMethod
    public void precondition() {
        homePage = new HomePage();
        homePage.open("https://the-internet.herokuapp.com/");
    }

    @Test(priority = 1, description = "Get formatted emails.")
    public void formattedEmails(){
        sortableDataTables = new SortableDataTables();
        homePage.clickOnItem(SORTABLE_DATA_TABLES);
        sortableDataTables.verifyPage();
        sortableDataTables.formattedEmail(1);
    }

    @Test(priority = 2, description = "Get sorted numbers.")
    public void sortedNumbers(){
        sortableDataTables.formattedNumbers();
    }
}
