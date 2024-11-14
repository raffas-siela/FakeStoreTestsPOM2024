package pageobjects;

import helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PaymentPage extends BasePage {
    private final By inputName = By.id("billing_first_name");
    private final By inputLastName = By.id("billing_last_name");
    private final By inputAdress = By.id("billing_address_1");
    private final By inputPostCode = By.id("billing_postcode");
    private final By inputCity = By.id("billing_city_field");
    private final By inputPhone = By.id("billing_phone");
    private final By inputCreditCard = By.id("stripe-card-element");
    private final By inputDateCard = By.id("stripe-exp-element");
    private final By inputCsvCode = By.id("stripe-cvc-element");


    public String currentUrl = driver.getCurrentUrl();
    protected PaymentPage(Browser browser) {
        super(browser);
    }
    private final By isOnOrderPage = By.cssSelector(".entry-header");

    public WebElement isOnPaymentPage() {
        return driver.findElement(isOnOrderPage);
    }
}