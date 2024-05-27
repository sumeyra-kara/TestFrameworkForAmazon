package sg.amazon.tests.end2endTest;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
    public void amazonTest() {
        try {
            extentLogger = reports.createTest("Amazon End To End Test");
            extentLogger.info("Login with valid credentials");
            Driver.getDriver().get(ConfigReader.getProperty("url"));
            loginPage.login();

            extentLogger.info("Verify that login is successful");
            Assert.assertTrue(basePage.accountName.getText().contains(ConfigReader.getProperty("username")));

            extentLogger.info("Erstellen Sie eine neue Liste unter Account and Lists");
            BrowserUtils.hover(basePage.accountAndLists);
            basePage.createAList.click();
            BrowserUtils.clickWithJS(yourListPage.createAListButton);
            BrowserUtils.waitFor(3);
            wait.until(ExpectedConditions.elementToBeClickable(yourListPage.createNewList)).click();

            extentLogger.info("Wählen Sie eine beliebige Kategorie links neben dem Suchfeld");
            BrowserUtils.waitFor(3);
            basePage.allMenuDropDown.click();
            basePage.productsNameInMenu().selectByVisibleText("Baby");

            extentLogger.info("Verifizieren Sie, dass die Kategorie ausgewählt wurde");
            Assert.assertTrue(basePage.productsNameInMenu().getFirstSelectedOption().getText().contains("Baby"));

            extentLogger.info("Geben Sie ein Produkt in das Suchfeld ein und suchen Sie");
            basePage.searchBox.sendKeys("Shampoo");
            basePage.searchButton.click();

            extentLogger.info("Verifizieren Sie, dass die Ergebnisse das Produkt enthalten");
            Assert.assertTrue(basePage.result.isDisplayed());
            extentLogger.pass("Test Passed!");

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            driver.quit();
        }
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
