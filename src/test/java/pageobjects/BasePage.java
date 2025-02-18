package pageobjects;
import helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public abstract class BasePage {
    private final By loadingIcon = By.cssSelector(".blockUI");
//d
    protected final WebDriver driver;
    protected final Browser browser;
    protected final String baseURL;
    protected BasePage (Browser browser){
        this.browser = browser;
        this.driver = browser.driver;
        baseURL = browser.baseURL;
        PageFactory.initElements(driver, this);
    }
    protected void waitForLoadingIcons(){
        browser.wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
    }
}