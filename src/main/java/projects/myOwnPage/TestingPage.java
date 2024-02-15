package projects.myOwnPage;

import base.BaseTestSelenide;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.*;

public class TestingPage extends BaseTestSelenide {
    private final ElementsCollection firstRow = $$x("//tbody/tr/td[1]");
    private final SelenideElement inputNameField = $("#username");
    private final SelenideElement checkbox = $("#vehicle1");
    private final SelenideElement fieldToSelect = $("#cars");
    private final SelenideElement buttonToClick = $x("//button");
    private final SelenideElement image = $x("//img");
    private final SelenideElement tag = $x("//p");
    private final SelenideElement link = $x("//a");

    public void printFirstRow() {

        System.out.println(firstRow.texts());
    }

    public void inputText() {
        inputNameField.sendKeys("Hello World!");
    }

    public void clickOnCheckBox() {
        checkbox.click();
    }

    public void selectElementInList() {
        fieldToSelect.selectOption("Opel");
    }

    public void clickOnButton() {
        buttonToClick.click();
    }

    public void verifyImage() {
        image.should(exist);                        // Проверка что картинка появилась
        Assert.assertTrue(image.isDisplayed());     // Проверка что картинка отображается на экране
    }

    public WebElement getTag() {
        return tag;
    }

    public TestingPage clickOnLink() {
        link.click();
        return this;
    }

}
