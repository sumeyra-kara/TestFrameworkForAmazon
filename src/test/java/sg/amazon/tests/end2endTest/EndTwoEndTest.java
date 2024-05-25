package sg.amazon.tests.end2endTest;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import sg.amazon.pages.BasePage;
import sg.amazon.pages.LoginPage;
import sg.amazon.pages.YourListPage;
import sg.amazon.tests.TestBase;
import sg.amazon.utilities.BrowserUtils;
import sg.amazon.utilities.ConfigReader;
import sg.amazon.utilities.Driver;

public class EndTwoEndTest extends TestBase {
    LoginPage loginPage=new LoginPage();
    YourListPage yourListPage=new YourListPage();
    BasePage basePage = new LoginPage();

    @Test
    public void test() {

        extentLogger = reports.createTest("Amazon End To End Test");

        extentLogger.info("Login with valid credentials");// 1
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        loginPage.login(); //2

        extentLogger.info("Verify that login is successful"); //3
        Assert.assertTrue(basePage.accountName.getText().contains(ConfigReader.getProperty("username")));

        extentLogger.info("Click Create a List Button");// 4
        BrowserUtils.hover(loginPage.accountAndLists);

        extentLogger.info("Erstellen Sie eine neue Liste unter Account and Lists");
        basePage.createAList.click();
        BrowserUtils.clickWithJS(yourListPage.createAListButton);
        WebElement create = wait.until(ExpectedConditions.elementToBeClickable(yourListPage.createNewList));
        create.click();

        extentLogger.info("Wählen Sie eine beliebige Kategorie links neben dem Suchfeld");// 5
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//select[@id='searchDropdownBox']")))).click();
        basePage.productsNameinMenu().selectByVisibleText("Baby");

        extentLogger.info("Verifizieren Sie, dass die Kategorie ausgewählt wurde");// 6
        Assert.assertTrue(basePage.productsNameinMenu().getFirstSelectedOption().getText().contains("Baby"));


        extentLogger.info("Geben Sie ein Produkt in das Suchfeld ein und suchen Sie");
        basePage.searchBox.sendKeys("Shampoo"); // 7
        basePage.searchButton.click();


        extentLogger.info("Verifizieren Sie, dass die Ergebnisse das Produkt enthalten"); //8
        Assert.assertTrue(basePage.result.isDisplayed());

        extentLogger.pass("Test Passed!");



    }



















}
/**
 * Gehen Sie https://www.amazon.sg/ --1
 * Akzeptieren, wenn Cookies vorhanden sind
 * Melden Sie sich mit gültigen Informationen an --2
 * Verifizieren Sie, dass die Anmeldung erfolgreich war---- 3
 * Erstellen Sie eine neue Liste unter "Account and Lists".--4
 * Wählen Sie eine beliebige Kategorie links neben dem Suchfeld --5
 * Verifizieren Sie, dass die Kategorie ausgewählt wurde ---6   ????
 * Geben Sie ein Produkt in das Suchfeld ein und suchen Sie---7
 * Verifizieren Sie, dass die Ergebnisse das Produkt enthalten--8
 */
