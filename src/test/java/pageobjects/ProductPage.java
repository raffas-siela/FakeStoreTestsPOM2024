package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends BasePage {
    private final By addToCart = By.cssSelector("[name=add-to-cart]");
    private final By goToCart = By.cssSelector(".woocommerce-message>.button");
    private final By addToWishlist = By.cssSelector(".add_to_wishlist");
    private final By loadingIcon = By.cssSelector(".blockUI");
    private final By goToWishlistFromHeader = By.cssSelector("#menu-item-248");

    public ProductPage(WebDriver driver){
        super(driver);
    }
    public ProductPage go(String productWspinFerURLSlug) {
        driver.get(baseURL + "/product/" + productWspinFerURLSlug);
        return this;
    }

    public ProductPage addToCart() {
        driver.findElement(addToCart).click();
        return this;
    }

    public CartPage goToCart() {
        driver.findElement(goToCart).click();
        return new CartPage(driver);
    }

    public ProductPage addToWishlist() {
        driver.findElement(addToWishlist).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
        return this;
    }

    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(driver);
    }
}
