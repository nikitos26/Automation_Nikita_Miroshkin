package projects.rabota_by.pages;

import base.BaseTestSelenide;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Selenide.*;

public class SearchPage extends BaseTestSelenide {
    private final ElementsCollection result = $$(".vacancy-serp-item__layout");
    private final SelenideElement title = $x("//h1[@data-qa=\"vacancies-catalog-header\"]");

    public List<String> getResult() {
        return result.shouldBe(sizeGreaterThan(0)).texts();
    }

    public String getTitleText() {
        System.out.println(title.getText());
        return title.getText();
    }
}
