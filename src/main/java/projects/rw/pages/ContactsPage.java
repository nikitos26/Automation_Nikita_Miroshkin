package projects.rw.pages;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class ContactsPage {
    private final SelenideElement title = $(".page-title");

    public ContactsPage verifyPage() {
        this.title.should(exist).shouldHave(text("Контакты"));
        return this;
    }
}
