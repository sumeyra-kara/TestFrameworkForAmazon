package sg.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourListPage extends BasePage{
    @FindBy(xpath = "//input[@class='a-button-input']")
    public WebElement createAListButton;

    @FindBy(xpath = "//span[@class='a-button a-button-primary']//span//input")
    public WebElement createNewList;


    @FindBy(xpath = "(//input[@class='a-button-input a-declarative'])[1]")
    public WebElement cancelButton;

    @FindBy(xpath = "//img[@alt='More Options']")
    public WebElement ucNokta;

    @FindBy(id = "editYourList")
    public WebElement manageList;

    @FindBy(xpath = "//span[text()='Delete list']")
    public WebElement deleteButton;


    @FindBy(xpath = "//span[@id='list-delete-confirm-announce']")
    public WebElement yeSButton;









}
