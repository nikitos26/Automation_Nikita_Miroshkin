package projects.rw.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import entities.rw.Railway;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class SearchPage {
    private final SelenideElement title = $x("//div[@class=\"sch-title__title h2\"]");
    private Map<String, List<String>> parsedData = new HashMap<>();
    private final SelenideElement table = $x("//div[@class=\"sch-table__body js-sort-body\"]");
    private final ElementsCollection tableData =table.$$(".sch-table__row");


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

    public SearchPage verifyStationInTitle(Railway railway) {
        String expected_text = railway.getStation();
        title.shouldBe(exist).shouldHave(text(expected_text));
        return this;
    }

    public SearchPage verifyDirectionInTitle(Railway railway) {
        String expected_text = railway.getFrom() + " — " + railway.getTo();
        title.should(exist).shouldHave(text(expected_text));
        return this;
    }

    public void addDataInMap(String key, String value) {
        if (this.parsedData.containsKey(key)) {
            this.parsedData.get(key).add(value);
        }else {
            List<String> newList = new ArrayList<>();
            newList.add(value);
            this.parsedData.put(key, newList);
        }
    }

    public void  parseTicketsResult() {
        for (SelenideElement data : this.tableData) {
            addDirectionInMap(data);
            addDepartureTimeInMap(data);
            addTravelTime(data);
        }

        printMap();
    }

    private void addTravelTime (SelenideElement element) {
        SelenideElement travelTime = element.$(".train-duration-time");
        if (!travelTime.exists()) {
            return;
        }
        addDataInMap("TravelTime", travelTime.getText());
    }

    private void addDepartureTimeInMap(SelenideElement element) {
        SelenideElement departureTime = element.$(".sch-table__time");
        if (!departureTime.exists()) {
            return;
        }
        addDataInMap("Departure Time", departureTime.getText());
    }

    private void printMap() {
        this.parsedData.forEach((key, values) -> {
            System.out.println("Key: " + key);
            values.forEach(value -> System.out.println("  Value: " + value));
        });
    }

    private void addDirectionInMap(SelenideElement element) {
        SelenideElement direction = element.$(".train-route");
        if (!direction.exists()) {
            return;
        }
        addDataInMap("Directon", direction.getText().replace("&nbsp;",""));
    }


}
