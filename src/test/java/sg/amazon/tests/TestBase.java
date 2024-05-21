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
    /*
 TestBase class'ini bu class'i parent edinecek class'larda driver olusturmamak ve ayarlari yeniden
 yapmamak icin kullaniyoruz.
  */
    protected WebDriver driver;
    protected JavascriptExecutor jse;
    protected WebDriverWait wait;
    protected Actions actions;
    protected ExtentReports reports; // extent report'a ilk atama yapar
    protected ExtentSparkReporter htmlReporter; // html raporu duzenler(eski sürümde ExtentHtmlReporter;)open in ile de dosyayi acabiliriz
    protected ExtentTest extentLogger; // test pass veya failed gibi bilgileri kaydeder


    @BeforeTest // test islemine baslamadan önce (tüm test isleminden önce)
    public void setUpTest() {
        reports = new ExtentReports(); // raporlamayi baslatir- um einige report zu erstellen, müssen wir diese Object benuzen
        String projectPath = System.getProperty("user.dir");
        String path = projectPath+"/test-output/reports.html";
        // olusturmak istedigimiz raporu(html formatinda) baslatiyoruz, path ile de dosya yolunu belirliyoruz
        htmlReporter = new ExtentSparkReporter(path);
        reports.attachReporter(htmlReporter);
        // istedigimiz bilgileri buraya ekleyebiliriz
        htmlReporter.config().setReportName("Batch16 Regression Test");
        reports.setSystemInfo("Enviroment","Produktion");
        reports.setSystemInfo("Browser", ConfigReader.getProperty("browser"));
        reports.setSystemInfo("OS", System.getProperty("os.name"));
        reports.setSystemInfo("Test Engineer", "Sümeyra");
    }

    @AfterTest
    public void tearDownTest() {
        reports.flush(); // raporlamayi sonlandirmak icin
    }


    @BeforeMethod
    public void setUp() {
        driver = Driver.getDriver();
        jse = (JavascriptExecutor) driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        actions=new Actions(driver);
    }

    @AfterMethod // her test methodundan sonra eger testte hata varsa ekran görüntüsünü alip rapora ekliyor.
    public void tearDown(ITestResult result) {

        if (result.getStatus()==ITestResult.FAILURE){ // eger testin sonucu basarisizsa
            extentLogger.fail(result.getName());
            String screenshotPath = BrowserUtils.takeScreenshot(result.getName());
            extentLogger.addScreenCaptureFromPath(screenshotPath);
            extentLogger.fail(result.getThrowable()); // exceptionmessage, hata mesaji listeler

        }

        //WebDriverFactory.wait(3);
        //Driver.closeDriver();
    }
}
