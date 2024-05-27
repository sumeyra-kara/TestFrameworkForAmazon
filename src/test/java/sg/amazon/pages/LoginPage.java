package sg.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sg.amazon.utilities.BrowserUtils;
import sg.amazon.utilities.ConfigReader;

public class LoginPage extends BasePage{
    @FindBy(id = "ap_email")
    public WebElement inputEmail;

    @FindBy(id = "continue")
    public WebElement continueButton;

    @FindBy(id = "ap_password")
    public WebElement inputPassword;

    @FindBy(id = "signInSubmit")
    public WebElement signInSubmitButton;

    public void login(){
        BrowserUtils.hover(accountAndLists);
        signInButton.click();
        inputEmail.sendKeys(ConfigReader.getProperty("email"));
        continueButton.click();
        inputPassword.sendKeys(ConfigReader.getProperty("password"));
        signInSubmitButton.click();
    }
}
