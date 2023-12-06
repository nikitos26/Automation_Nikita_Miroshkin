package lesson_5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static driver.SimpleWebDriver.*;


public class HeatingCalculator {

    private String url = "https://kermi-fko.ru/raschety/Calc-Rehau-Solelec.aspx";

    @BeforeTest
    public void setUpDriver() {
        setDriver(url);
    }

    @AfterTest
    public void driverQuit() {
        quit();
    }

    public void setValue(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value, Keys.ENTER);

    }


    public void heatingCalculator(String roomLength, String roomWidth, String roomName, String heatingType,
                                  String floorCablePower, String specFloorCablePower) {
        WebElement Length = getDriver().findElement(By.id("el_f_lenght"));
        setValue(Length, roomLength);

        WebElement Width = getDriver().findElement(By.id("el_f_width"));
        setValue(Width, roomWidth);

        Select room = new Select(getDriver().findElement(By.id("room_type")));
        room.selectByVisibleText(roomName);

        Select heating = new Select(getDriver().findElement(By.id("heating_type")));
        heating.selectByVisibleText(heatingType);

        getDriver().findElement(By.name("button")).click();

        Assert.assertEquals(floorCablePower, getDriver().findElement(By.id("floor_cable_power")).getAttribute("value"));
        Assert.assertEquals(specFloorCablePower, getDriver().findElement(By.id("spec_floor_cable_power")).getAttribute("value"));
    }

    @Test
    public void calculationKitchenGeneralHeating() {
        heatingCalculator("4", "5", "Кухня или спальня", "Основное отопление",
                "2400", "120");
    }

    @Test
    public void calculationBathroomComfortableHeating() {
        heatingCalculator("6", "7", "Ванная", "Подогрев для комфорта",
                "3116", "74");
    }

    @Test
    public void calculationBathroomGeneralHeating() {
        heatingCalculator("8", "10", "Ванная", "Основное отопление",
                "11200", "140");
    }
}
