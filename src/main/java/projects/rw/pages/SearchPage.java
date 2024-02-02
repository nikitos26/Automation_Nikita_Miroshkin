package projects.rw.pages;

import com.codeborne.selenide.SelenideElement;
import entities.rw.Directions;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage {
    private final SelenideElement title = $x("//div[@class=\"sch-title__title h2\"]");



    public SearchPage verifyDirectionInTitle(String from, String to) {
        String expected_text = from + " — " + to;
        title.should(exist).shouldHave(text(expected_text));
        return this;
    }

    public SearchPage verifyDirectionInTitle(String to) {
        String expected_text = to;
        title.shouldBe(exist).shouldHave(text(expected_text));
        return this;
    }

    public SearchPage verifyDirectionInTitle(Directions directions) {
        String expected_text = directions.getFrom() + " — " + directions.getTo();
        title.should(exist).shouldHave(text(expected_text));
        return this;
    }


}
