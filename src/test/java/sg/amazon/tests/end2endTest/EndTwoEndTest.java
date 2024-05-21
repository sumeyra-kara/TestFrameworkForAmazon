package sg.amazon.tests.end2endTest;

import org.testng.Assert;
import org.testng.annotations.Test;
import sg.amazon.pages.LoginPage;
import sg.amazon.tests.TestBase;
import sg.amazon.utilities.BrowserUtils;
import sg.amazon.utilities.ConfigReader;
import sg.amazon.utilities.Driver;

public class EndTwoEndTest extends TestBase {
    LoginPage loginPage=new LoginPage();

    @Test
    public void test() {

        extentLogger = reports.createTest("Amazon End To End Test");

        extentLogger.info("Login with valid credentials");
        Driver.getDriver().get(ConfigReader.getProperty("url"));
        loginPage.login();
        extentLogger.info("Verify that login is successful");
        Assert.assertTrue(loginPage.accountName.getText().contains(ConfigReader.getProperty("username")));
        extentLogger.info("Click Create a List Button");
        BrowserUtils.hover(loginPage.accountAndLists);
        loginPage.createAList.click();


    }
}
/**
 * Gehen Sie https://www.amazon.sg/
 * Akzeptieren, wenn Cookies vorhanden sind
 * Melden Sie sich mit gültigen Informationen an
 * Verifizieren Sie, dass die Anmeldung erfolgreich war----
 * Erstellen Sie eine neue Liste unter "Account and Lists".
 * Wählen Sie eine beliebige Kategorie links neben dem Suchfeld
 * Verifizieren Sie, dass die Kategorie ausgewählt wurde
 * Geben Sie ein Produkt in das Suchfeld ein und suchen Sie
 * Verifizieren Sie, dass die Ergebnisse das Produkt enthalten
 */
