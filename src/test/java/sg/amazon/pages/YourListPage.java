package sg.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YourListPage extends BasePage{
    @FindBy(xpath = "//input[@class='a-button-input']")
    public WebElement createAListButton;

    @FindBy(xpath = "//div[@id='nav-al-wishlist']//span[@class='nav-text']")
    public List<WebElement> list;

    @FindBy(xpath = "(//div[@class='aok-inline-block aok-align-center'])[1]")
    public WebElement more;

    @FindBy(xpath = "//a[text()='Manage list']")
    public WebElement manageList;

    @FindBy(xpath = "//span[text()='Delete list']")
    public WebElement deleteList;

    @FindBy(xpath = "//span[text()='Yes']")
    public WebElement yesButton;

    @FindBy(xpath = "//span[@id='wl-redesigned-create-list']//span[text()='Create List']")
    public WebElement innerCreateList;















}
