package projects.rabota_by.pages;

import base.BaseTestSelenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$;

public class HomePage extends BaseTestSelenide {
    private final SelenideElement searchField = $(".bloko-input-text_scale-large");
    private final SelenideElement searchButton = $(".supernova-search-submit-text");

    public HomePage inputJob(String job) {
        searchField.shouldBe(exist).sendKeys(job);
        return this;
    }

    public HomePage clickSearchButton() {
        searchButton.should(exist).click();
        return this;
    }
}
