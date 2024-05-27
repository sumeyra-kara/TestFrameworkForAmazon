package sg.amazon.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class Driver {
    private Driver() {
    }
    private static WebDriver driver;
    public static WebDriver getDriver() {
        String browser = ConfigReader.getProperty("browser");
        if (driver == null) {
            switch (browser.toLowerCase()) {
                case "chrome":
                    driver = new ChromeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    break;
                case "chrome-headless":
                    driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));
                    break;
                case "firefox":
                    driver = new FirefoxDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    break;
                case "firefox-headless":

                    driver = new FirefoxDriver(new FirefoxOptions().addArguments("--headless"));
                    break;
                case "edge":
                    if (System.getProperty("os.name").contains("MAC")) {
                        throw new WebDriverException("Your Operating System does not support");
                    }
                    driver = new EdgeDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    break;
                case "ie":
                    if (System.getProperty("os.name").toLowerCase().contains("MAC")) {
                        throw new WebDriverException("Your operating system does not support the requested browser");
                    }
                    driver = new InternetExplorerDriver();
                    break;
                case "safari":
                    if (System.getProperty("os.name").contains("WINDOWS")){
                        throw new WebDriverException("Your Operating System does not support");
                    }
                    driver = new SafariDriver();
                    driver.manage().window().maximize();
                    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
                    break;
                default:
                    System.out.println("Invalid driver");
            }
        }

        return driver;
    }

    public static void closeDriver(){
        if (driver != null){
            driver.quit();
            driver = null;
        }
    }
}
