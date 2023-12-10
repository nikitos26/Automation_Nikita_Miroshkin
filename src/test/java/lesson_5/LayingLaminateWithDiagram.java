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


public class LayingLaminateWithDiagram {

    @BeforeTest
    public void setUpDriver() {
        setDriver("https://masterskayapola.ru/kalkulyator/laminata.html");
    }

    @AfterTest
    public void driverQuit() {
        quit();
    }

    static void setValue(WebElement element, String value) {
        element.sendKeys(Keys.chord(Keys.COMMAND, "a"), value, Keys.ENTER);
    }

    public void calculatingLaminate(String roomWidth, String roomHeight, String lamWidth, String lamHeight,
                                    String inPack, String price, String direct, String bias,
                                    String wallDist, String layingArea, String amountPanels,
                                    String amountPackages, String priceLaminate,
                                    String leftovers, String segments) {
        setValue(getDriver().findElement(By.name("calc_roomwidth")), roomWidth);
        setValue(getDriver().findElement(By.name("calc_roomheight")), roomHeight);
        setValue(getDriver().findElement(By.name("calc_lamwidth")), lamWidth);
        setValue(getDriver().findElement(By.name("calc_lamheight")), lamHeight);
        setValue(getDriver().findElement(By.name("calc_inpack")), inPack);
        setValue(getDriver().findElement(By.name("calc_price")), price);

        Select opt = new Select(getDriver().findElement(By.name("calc_direct")));
        opt.selectByVisibleText(direct);

        setValue(getDriver().findElement(By.name("calc_bias")), bias);
        setValue(getDriver().findElement(By.name("calc_walldist")), wallDist);
        getDriver().findElement(By.xpath("//*[@id='inputcalc']/div/div[3]/div[7]/div/input")).click();

        Assert.assertEquals(layingArea, getDriver().findElement(By.xpath("//*[@id='s_lam']/b")).getText());
        Assert.assertEquals(amountPanels, getDriver().findElement(By.xpath("//*[@id='l_count']/b")).getText());
        Assert.assertEquals(amountPackages, getDriver().findElement(By.xpath("//*[@id='l_packs']/b")).getText());
        Assert.assertEquals(priceLaminate, getDriver().findElement(By.xpath("//*[@id='l_price']/b")).getText());
        Assert.assertEquals(leftovers, getDriver().findElement(By.xpath("//*[@id='l_over']/b")).getText());
        Assert.assertEquals(leftovers, getDriver().findElement(By.xpath("//*[@id='l_over']/b")).getText());
        Assert.assertEquals(segments, getDriver().findElement(By.xpath("//*[@id='l_trash']/b")).getText());

    }

    @Test
    public void test1() {
        calculatingLaminate("9", "5", "1500", "500", "10", "1000",
                "По ширине комнаты", "300", "30", "44.16", "62",
                "7", "52500", "8", "6");
    }

    @Test
    public void test2() {
        calculatingLaminate("10", "6", "1800", "600", "13", "1200",
                "По длине комнаты", "300", "30", "59.04", "57",
                "5", "84240", "8", "4");
    }

    @Test
    public void test3() {
        calculatingLaminate("13", "8", "2000", "800", "12", "1500",
                "По длине комнаты", "300", "30", "102.74", "67",
                "6", "172800", "5", "6");
    }
}
