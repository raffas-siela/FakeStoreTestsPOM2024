import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private By productItem = By.cssSelector("tr.cart_item");
    private By quantityField = By.cssSelector("input.qty");
    private By updateCartButton = By.cssSelector("[name=update_cart]");
    private By totalPrice = By.cssSelector(".cart-subtotal [data-title=Kwota]");
    private By loadingIcon = By.cssSelector(".blockUI");
    private By emptyCartInfo = By.cssSelector(".entry-content .cart-empty");
    private final WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void go() {
        String baseURL = "https://fakestore.testelka.pl/";
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
}
