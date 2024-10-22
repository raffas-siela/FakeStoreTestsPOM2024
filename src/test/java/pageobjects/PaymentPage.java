package pageobjects;

import helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage {
    public String currentUrl = driver.getCurrentUrl();


    protected PaymentPage(Browser browser) {
        super(browser);
    }
    private final By isOnOrderPage = By.cssSelector(".entry-header");

    public WebElement isOnPaymentPage() {
        return driver.findElement(isOnOrderPage);
    }

}