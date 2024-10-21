package pageobjects;
import helpers.Browser;
import org.openqa.selenium.By;

public class ProductPage extends BasePage {
    private final By addToCart = By.cssSelector("[name=add-to-cart]");
    private final By goToCart = By.cssSelector(".woocommerce-message>.button");
    private final By addToWishlist = By.cssSelector(".add_to_wishlist");
    private final By goToWishlistFromHeader = By.cssSelector(".menu-item-248");
    public final StoreHeaderComponent storeHeader;
    public ProductPage(Browser browser){
        super(browser);
        storeHeader = new StoreHeaderComponent(browser);
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
        return new CartPage(browser);
    }
    public ProductPage closeInfoButton() {
        driver.findElement(By.className("woocommerce-store-notice__dismiss-link")).click();
        return this;
    }

    public ProductPage addToWishlist() {
        driver.findElement(addToWishlist).click();
        waitForLoadingIcons();
        return this;
    }

    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(browser);
    }
}
