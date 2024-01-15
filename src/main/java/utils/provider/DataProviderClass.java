package utils.provider;

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

    @DataProvider(name = "product names")
    public Object[][] productNames() {
        return new Object[][]{{"Backpack"}, {"Bike Light"}, {"Bolt T-Shirt"}};
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
}

