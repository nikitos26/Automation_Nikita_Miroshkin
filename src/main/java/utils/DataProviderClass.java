package utils;

import org.testng.annotations.DataProvider;

public class DataProviderClass {

    @DataProvider(name = "wrong user data")
    public Object[][] getUserNames() {
        return new Object[][]{
                {"Alex", "secret_sauce"},
                {"Bob", "secret_sauce"},
                {"John", "secret_sauce"}};
    }

    @DataProvider(name="product names")
    public Object[][] productNames() {
        return new Object[][]{{"Backpack"},{"Bike Light"},{"Bolt T-Shirt"}};
    }

}

