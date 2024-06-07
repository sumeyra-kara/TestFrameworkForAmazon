package sg.amazon.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import sg.amazon.utilities.BrowserUtils;
import sg.amazon.utilities.ConfigReader;
import sg.amazon.utilities.Driver;

import java.time.Duration;

public class TestBase {
    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected WebDriverWait wait;
    protected Actions actions;
    protected ExtentReports reports;
    protected ExtentSparkReporter htmlReporter;
    protected ExtentTest extentLogger;
    @BeforeTest
    public void setUpTest() {
        reports = new ExtentReports();
        String projectPath = System.getProperty("user.dir");
        String path = projectPath+"/test-output/reports.html";
        htmlReporter = new ExtentSparkReporter(path);
        reports.attachReporter(htmlReporter);
        htmlReporter.config().setReportName("Batch16 Regression Test");
        reports.setSystemInfo("Enviroment","Produktion");
        reports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        reports.setSystemInfo("OS", System.getProperty("os.name"));
        reports.setSystemInfo("Test Engineer", "Sümeyra");
    }

    @AfterTest
    public void tearDownTest() {
        reports.flush();
    }

    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions=new Actions(driver);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus()==ITestResult.FAILURE){
            extentLogger.fail(result.getName());
            String screenshotPath = BrowserUtils.takeScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotPath);
            extentLogger.fail(result.getThrowable());
        }
        Driver.closeDriver();
    }
}
