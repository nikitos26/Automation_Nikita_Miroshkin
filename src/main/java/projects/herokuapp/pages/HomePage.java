package projects.herokuapp.pages;

import base.BasePage;
import org.openqa.selenium.By;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class HomePage extends BasePage {
    private By getItemLocator(String item) {
        return By.linkText(item);
    }

    public void open(String url) {
        navigateTo(url);
    }

    public void clickOnItem(HomePageItems item) {
        wait.until(elementToBeClickable(getItemLocator(item.getItem())));
        click(getItemLocator(item.getItem()));
    }
}
