package pageobjects;
import org.openqa.selenium.WebDriver;
import helpers.Browser;

public class MainPage extends BasePage{
    public final StoreHeaderComponent storeHeader;
    public MainPage(Browser browser) {
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
    }

    public MainPage go() {
        driver.get(baseURL);
        return this;
    }
}
