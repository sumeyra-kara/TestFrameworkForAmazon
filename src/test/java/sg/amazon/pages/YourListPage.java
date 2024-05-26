package sg.amazon.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class YourListPage extends BasePage{
    @FindBy(xpath = "//input[@class='a-button-input']")
    public WebElement createAListButton;

    @FindBy(xpath = "//span[@class='a-button a-button-primary']//span//input")
    public WebElement createNewList;









}
