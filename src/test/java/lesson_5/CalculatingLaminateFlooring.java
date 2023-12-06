package lesson_5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


import static driver.SimpleWebDriver.*;

public class CalculatingLaminateFlooring {

    @BeforeTest
    public void setUpDriver() {
        setDriver("https://calc.by/building-calculators/laminate.html");
    }

    @AfterTest
    public void driverQuit() {
        quit();
    }

    static void setValue(WebElement element, String value){
        element.clear();
        element.sendKeys(value);
    }

    @Test
    public void calculatingLaminate() {
        getDriver().findElement(By.xpath("//*[@id=\"qc-cmp2-ui\"]/div[2]/div/button[3]/span")).click();
        setValue(getDriver().findElement(By.id("ln_room_id")), "500");

        setValue(getDriver().findElement(By.id("wd_room_id")), "400");

        setValue(getDriver().findElement(By.id("ln_lam_id")), "1300");

        setValue(getDriver().findElement(By.id("wd_lam_id")), "200");

        setValue(getDriver().findElement(By.id("n_packing")), "10");

        Select opt = new Select(getDriver().findElement(By.id("laying_method_laminate")));
        opt.selectByVisibleText("со смещение на 1/3 длины");

        setValue(getDriver().findElement(By.id("indent_walls_id")), "10");

        getDriver().findElement(By.id("direction-laminate-id1")).click();
        getDriver().findElement(By.xpath("//*[@id=\"t3-content\"]/div[3]/article/section/div[2]/div[2]/div[2]/a")).click();

        Assert.assertEquals("84", getDriver().findElement(By.cssSelector("span[style*='color:#C80303; font-weight:bold;']")).getText());
        Assert.assertEquals("9", getDriver().findElement(By.cssSelector("span[style*='color:#0E8C19; font-weight:bold;']")).getText());
    }
}
