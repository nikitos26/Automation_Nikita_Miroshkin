package utils.provider;

import entities.rw.Railway;
import entities.sausedemo.Product;
import entities.sausedemo.UserBuilder;
import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "wrong user data")
    public Object[][] getUserNames() {
        return new Object[][]{
                {"Alex", "secret_sauce"},
                {"Bob", "secret_sauce"},
                {"John", "secret_sauce"}};
    }

    @DataProvider(name = "wrong user data from builder")
    public Object[][] getUserNamesFromBuilder() {
        return new Object[][]{
                {new UserBuilder.Builder()
                        .withUserName("Alex")
                        .withPassword("secret_sauce")
                        .build()},
                {new UserBuilder.Builder()
                        .withUserName("Bob")
                        .withPassword("secret_sauce")
                        .build()},
                {new UserBuilder.Builder()
                        .withUserName("John")
                        .withPassword("secret_sauce")
                        .build()}
        };
    };

    @DataProvider(name = "Successful user login from builder")
    public Object[][] passedData() {
        return new Object[][]{
                {new UserBuilder.Builder()
                        .withUserName("standard_user")
                        .withPassword("secret_sauce")
                        .build()}
        };
    }

    @DataProvider(name = "product names")
    public Object[][] productNames() {
        return new Object[][]{{"Backpack"}, {"Bike Light"}, {"Bolt T-Shirt"}};
    }

    @DataProvider(name = "product names from builder")
    public Object[][] productNamesFromBuilder() {
        return new Object[][]{
                {new Product.ProductBuilder().productName("Backpack").build()},
                {new Product.ProductBuilder().productName("Bike Light").build()},
                {new Product.ProductBuilder().productName("Bolt T-Shirt").build()}
        };
    }

    @DataProvider(name = "get wrong user data from builder")
    public Object[][] getUserData() {
        return new Object[][]{
                {new UserBuilder.Builder()
                        .withUserName("test")
                        .withPassword("test")
                        .build()},
                {new UserBuilder.Builder()
                        .withUserName("locked_user")
                        .withPassword("secret_sauce")
                        .build()},
                {new UserBuilder.Builder()
                        .withUserName("problem_user")
                        .withPassword("sauce")
                        .build()}
        };
    }

    @DataProvider(name = "Directions for travel.")
    public Object[][] getRailwayDirections() {
        return new Object[][]{
                {new Railway.RailwayBuilder().from("Минск").to("Витебск").build()},
                {new Railway.RailwayBuilder().from("Витебск").to("Молодечно").build()},
                {new Railway.RailwayBuilder().from("Брест").to("Гомель").build()}
        };
    }

    @DataProvider(name = "Railway stations.")
    public Object[][] getRailwayStations() {
        return new Object[][]{
                {new Railway.RailwayBuilder().station("Минск-Пассажирский").build()},
                {new Railway.RailwayBuilder().station("Орша-Центральная").build()},
                {new Railway.RailwayBuilder().station("Смолевичи").build()}
        };
    }
}