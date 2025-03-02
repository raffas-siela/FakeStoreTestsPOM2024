package pageobjects;
import org.openqa.selenium.By;
import helpers.Browser;
public class CartPage extends BasePage {
    private final By productItem = By.cssSelector("tr.cart_item");
    private final By quantityField = By.cssSelector("input.qty");
    private final By updateCartButton = By.cssSelector("[name=update_cart]");
    private final By totalPrice = By.cssSelector(".cart-subtotal [data-title=Kwota]");
    private final By totalPriceCoupon = By.cssSelector(".order-total .woocommerce-Price-amount");
    private final By emptyCartInfo = By.cssSelector(".entry-content .cart-empty");
    private final By paymentButton = By.linkText("Przejdź do płatności");
    private final By couponField = By.id("coupon_code");
    private final By applyCouponButton = By.name("apply_coupon");
    private final By successMessage = By.cssSelector(".woocommerce .woocommerce-notices-wrapper");
    public CartPage(Browser browser) {
        super(browser);
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
        waitForLoadingIcons();
        return this;
    }
    public String getTotalPrice() {
        return driver.findElement(totalPrice).getText();
    }
    public CartPage removeFromCart() {
        driver.findElement(By.cssSelector(".woocommerce-cart-form__cart-item .remove")).click();
        waitForLoadingIcons();
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
    public PaymentPage goToPayment(){
        driver.findElement(paymentButton).click();
        return new PaymentPage(browser);
    }
    public CartPage applyCoupon(String coupon) {
        driver.findElement(couponField).sendKeys(coupon);
        driver.findElement(applyCouponButton).click();
        waitForLoadingIcons();
        return this;
    }
    public String getSuccessMessage() {
        return driver.findElement(successMessage).getText();
    }

    public String getTotalPriceCoupon() {
        return driver.findElement(totalPriceCoupon).getText();
    }
}