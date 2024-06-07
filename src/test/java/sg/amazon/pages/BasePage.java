package sg.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import sg.amazon.utilities.Driver;

public abstract class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy (css = "[class='nav-line-2 ']")
    public WebElement accountAndLists;

    @FindBy (xpath= "//div[@id='nav-flyout-ya-signin']/a")
    public WebElement signInButton;

    @FindBy (id= "nav-link-accountList-nav-line-1")
    public WebElement accountName;

    @FindBy (xpath= "//select[@id='searchDropdownBox']")
    public WebElement allMenuDropDown;

    public Select productsNameInMenu(){
        Select select = new Select(allMenuDropDown);
        return select;
    }

    @FindBy (id= "twotabsearchtextbox")
    public WebElement searchBox;

    @FindBy (id= "nav-search-submit-button")
    public WebElement searchButton;

    @FindBy (xpath= "//span[contains(text(), 'Shampoo')]")
    public WebElement result;






}
