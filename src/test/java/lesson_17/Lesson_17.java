package lesson_17;

import com.codeborne.selenide.Selenide;
import org.openqa.selenium.By;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.*;

public class Lesson_17 {

    @BeforeTest
    public void precondition() {
        open("https://www.rw.by/");
    }

    @Test
    public void test() {
        $("#acFrom").sendKeys("Витебск");
        $x("//input[@id=\"acTo\"]").sendKeys("Минск");
        $(By.xpath("//span[@class=\"std-button\"]")).click();
    }

}
