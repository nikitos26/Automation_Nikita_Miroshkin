package base;

import com.codeborne.selenide.Configuration;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static com.codeborne.selenide.WebDriverRunner.driver;

public abstract class BaseTestSelenide {
    protected <T> T get(Class<T> classPage) {
        if (driver().hasWebDriverStarted()) {
            return page(classPage);
        }
        return open(Configuration.baseUrl, classPage);
    }

}
