package sg.amazon.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    static {
        String path = "configuration.properties";
        try {
            FileInputStream fis = new FileInputStream(path); //configuration.properties'e ulasmak icin yazilir
            properties = new Properties();
            properties.load(fis); //configuration.properties'ten alinan bilgileri properties objesine yüklemek icin
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
