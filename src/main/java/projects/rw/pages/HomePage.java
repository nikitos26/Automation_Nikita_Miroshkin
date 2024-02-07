package projects.rw.pages;

import com.codeborne.selenide.SelenideElement;
import entities.rw.Railway;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;

public class HomePage {
    private SelenideElement from = $("#acFrom");
    private SelenideElement to = $x("//input[@id=\"acTo\"]");
    private SelenideElement search = $(By.xpath("//span[@class=\"std-button\"]"));
    private SelenideElement byRoutes = $("#tabForm1");
    private SelenideElement byStations = $("#tabForm2");
    private final SelenideElement contacts = $x("//a[@href=\"/corporate/contacts/\"]");
    private final SelenideElement stationField = $(byName("station"));
    private final SelenideElement proceed = $x("//button[@id=\"proceed-button\"]");

    public HomePage enterFrom(String from) {
        this.from.should(visible).sendKeys(from);
        return this;
    }

    public HomePage enterFrom(Railway railway) {
        this.from.should(visible).sendKeys(railway.getFrom());
        return this;
    }

    public HomePage enterTo(String to) {
        this.to.should(visible).sendKeys(to);
        return this;
    }

    public HomePage enterStation(String to) {
        this.stationField.shouldBe(visible).sendKeys(to);
        return this;
    }

    public HomePage enterStation(Railway railway) {
        this.stationField.shouldBe(visible).sendKeys(railway.getStation());
        return this;
    }


    public HomePage enterTo(Railway railway) {
        this.to.should(visible).sendKeys(railway.getTo());
        return this;
    }

    public HomePage clickSearch() {
        this.search.should(enabled).click();
        return this;
    }

    public HomePage clickRoutes() {
        this.byRoutes.should(exist).click();
        return this;
    }

    public HomePage clickStations() {
        this.byStations.shouldBe(exist).click();
        return this;
    }

    public HomePage clickContacts() {
        this.contacts.shouldBe(exist).click();
        return this;
    }

    public void openHomePage() {
        String expectedUrl = "https://www.rw.by/";
        String currentUrl = url();
        System.out.println(currentUrl);
        if (!currentUrl.equals(expectedUrl)) {
            open(expectedUrl);
        }
    }

    public HomePage clickProceedButton() {
        System.out.println("Wait element");
        this.proceed.shouldBe(appear).click();
        actions().click(proceed);
        System.out.println("Click on element");
        return this;
    }
}
