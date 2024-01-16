package utils.properties;

import lombok.extern.log4j.Log4j;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Log4j
public class PropertyReader {
    private static Properties properties;

    public static void setUpProperty(String fileName) {
        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/" + fileName + ".properties" ));
        } catch (IOException e) {
            log.error("Error");
            throw new RuntimeException(e);
        }
    }

    public static Properties getProperties() {
        return properties;
    }
}
