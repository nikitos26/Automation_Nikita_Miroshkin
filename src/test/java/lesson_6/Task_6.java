package lesson_6;

import lombok.extern.log4j.Log4j;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static driver.SimpleWebDriver.*;

// Сделал два идентичных теста для практики работы с локаторами, из-за этого не стал разбивать метод на подметоды

@Log4j
public class Task_6 {
    private String url = "https://www.saucedemo.com/";
    private String password = "secret_sauce";
    private String login = "standard_user";
    private List<String> pricesInCatalog;
    private List<String> pricesInCart;

    public List<String> gettingPrices(String NameCssLocator) {   //
        List<String> prices = getDriver().findElements(By.cssSelector(NameCssLocator))
                .stream().map(WebElement::getText)
                .collect(Collectors.toList());
        return prices;
    }

    @BeforeTest
    public void SetUp() {
        setDriver(url);
    }

    @Test
    public void test_1() {
        // Авторизация на сайте
        getDriver().findElement(By.id("user-name")).sendKeys(login, Keys.ENTER);
        getDriver().findElement(By.name("password")).sendKeys(password, Keys.ENTER);
        getDriver().findElement(By.className("submit-button")).click();

        // Проверяем переход в каталог стоварами
        Assert.assertTrue(getDriver().findElement(By.cssSelector("#inventory_container")).isDisplayed(), "Catalog hasn't opened");

        // Добавление первый товаров в карзину
        String firstGoodNameInCatalog = getDriver().findElement(By.linkText("Sauce Labs Backpack")).getText();
        log.info("firstGoodName " + firstGoodNameInCatalog);
        getDriver().findElement(By.xpath("//button[@name='add-to-cart-sauce-labs-backpack']")).click();

        // Добавление второй товаров в карзину
        String secondGoodNameInCatalog = getDriver().findElement(By.partialLinkText("Bike Light")).getText();
        log.info("secondGoodNameInCatalog " + secondGoodNameInCatalog);
        getDriver().findElement(By.xpath("//button[contains(@data-test, 'bike-light')]")).click();
        pricesInCatalog = gettingPrices(".inventory_item_price");

        //Открытие карзины
        getDriver().findElement(By.cssSelector(".shopping_cart_link")).click();
        // Проверяем что корзина открылась. Появлись дерево елементов с товарами положенными в  корзины + Кнопка checkout
        Assert.assertTrue(getDriver().findElement(By.id("cart_contents_container")).isDisplayed(), "Cars hasn't open!");
        Assert.assertTrue(getDriver().findElement(By.xpath("//button[text()='Checkout']")).isDisplayed(), "Cars hasn't open!");

        pricesInCart = gettingPrices(".inventory_item_price");

        String firstGoodNameInInCart = getDriver().findElement(By.cssSelector("#item_4_title_link>div")).getText();
        String secondGoodNameInCart = getDriver().findElement(By.cssSelector("#item_0_title_link .inventory_item_name")).getText();

        // Проверяем название товаров
        Assert.assertEquals(firstGoodNameInCatalog, firstGoodNameInInCart);
        Assert.assertEquals(secondGoodNameInCatalog, secondGoodNameInCart);

        // Проверяем цены товаров
        Assert.assertEquals(pricesInCatalog.get(0), pricesInCart.get(0));
        Assert.assertEquals(pricesInCatalog.get(1), pricesInCart.get(1));
    }

    @Test
    public void test_2() {
        // Авторизация на сайте
        getDriver().findElement(By.cssSelector(".input_error.form_input")).sendKeys(login, Keys.ENTER);
        getDriver().findElement(By.cssSelector("[type^='pass']")).sendKeys(password, Keys.ENTER);
        getDriver().findElement(By.cssSelector("[value$='gin']")).click();

        // Проверяем переход в каталог стоварами
        Assert.assertTrue(getDriver().findElement(By.xpath("//div[@id='contents_wrapper']//descendant::div[@class='inventory_container' and @id ='inventory_container']")).isDisplayed(), "Catalog hasn't opened");

        // Добавление первый товаров в карзину
        String firstGoodNameInCatalog = getDriver().findElement(By.xpath("//div[contains(text(),'Backpack')]")).getText();
        log.info("firstGoodName " + firstGoodNameInCatalog);
        getDriver().findElement(By.cssSelector("[name='add-to-cart-sauce-labs-backpack']")).click();

        // Добавление второй товаров в карзину
        String secondGoodNameInCatalog = getDriver().findElement(By.partialLinkText("Bike Light")).getText();
        log.info("secondGoodNameInCatalog " + secondGoodNameInCatalog);
        getDriver().findElement(By.cssSelector("[data-test*='bike-light']")).click();
        pricesInCatalog = gettingPrices(".inventory_item_price");

        //Открытие карзины
        getDriver().findElement(By.cssSelector("a.shopping_cart_link")).click();
        // Проверяем что корзина открылась. Появлись дерево елементов с товарами положенными в  корзины + Кнопка checkout
        Assert.assertTrue(getDriver().findElement(By.id("cart_contents_container")).isDisplayed(), "Cars hasn't open!");
        Assert.assertTrue(getDriver().findElement(By.cssSelector("[data-test~='checkout']")).isDisplayed(), "Cars hasn't open!");

        pricesInCart = gettingPrices(".inventory_item_price");

        String firstGoodNameInInCart = getDriver().findElement(By.cssSelector("#item_4_title_link>div")).getText();
        String secondGoodNameInCart = getDriver().findElement(By.cssSelector("#item_0_title_link .inventory_item_name")).getText();

        // Проверяем название товаров
        Assert.assertEquals(firstGoodNameInCatalog, firstGoodNameInInCart);
        Assert.assertEquals(secondGoodNameInCatalog, secondGoodNameInCart);

        // Проверяем цены товаров
        Assert.assertEquals(pricesInCatalog.get(0), pricesInCart.get(0));
        Assert.assertEquals(pricesInCatalog.get(1), pricesInCart.get(1));

    }

    public void removeGoodsInCart() {
        List<WebElement> goodsInCart = getDriver().findElements(By.cssSelector("[id*='remove']"));
        for (WebElement good : goodsInCart) {
            good.click();
        }
    }

    @AfterTest
    public void driverQuit() {
        quit();
    }

    @AfterMethod
    public void tearDown() {
        removeGoodsInCart();
        getDriver().navigate().refresh();
        getDriver().manage().deleteAllCookies();
        getDriver().navigate().to(url);
    }
}