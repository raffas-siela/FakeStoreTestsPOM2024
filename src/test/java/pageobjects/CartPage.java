package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage extends BasePage {

    private final By productItem = By.cssSelector("tr.cart_item");
    private final By quantityField = By.cssSelector("input.qty");
    private final By updateCartButton = By.cssSelector("[name=update_cart]");
    private final By totalPrice = By.cssSelector(".cart-subtotal [data-title=Kwota]");
    private final By loadingIcon = By.cssSelector(".blockUI");
    private final By emptyCartInfo = By.cssSelector(".entry-content .cart-empty");
    private final By goToPayButton = By.className("wc-forward");
    private final By hidingInfo = By.cssSelector(".woocommerce-store-notice__dismiss-link");

    private  WebDriver driver;
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public void go() {
        driver.get(baseURL + "/koszyk/");
    }

    public int getNumberOfProducts() {
        return driver.findElements(productItem).size();
    }

    public CartPage changeQuantity(int quantity) {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(String.valueOf(quantity));
        driver.findElement(updateCartButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
        return this;
    }
    public CartPage increaseQuantityButton(int quantity) {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(String.valueOf(quantity));
        driver.findElement(updateCartButton).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
        return this;
    }

    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }

    public CartPage removeFromCart() {
        driver.findElement(By.cssSelector(".woocommerce-cart-form__cart-item .remove")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
        return this;
    }

    public String getInfoEmpty() {
        return driver.findElement(emptyCartInfo).getText();
    }

    public boolean updateButtonIsEnabled() {
        return driver.findElement(updateCartButton).isEnabled();
    }

    public CartPage changeQuantityWithoutRefresh(int quantity) {
        driver.findElement(quantityField).clear();
        driver.findElement(quantityField).sendKeys(String.valueOf(quantity));
        return this;
    }
}
