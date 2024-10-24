package pageobjects;
import org.openqa.selenium.By;
import helpers.Browser;

public class WishlistPage extends BasePage{
    private final By productItems = By.cssSelector(".wishlist-items-wrapper .product-remove");
    protected WishlistPage(Browser browser) {
        super(browser);
    }
    public int getNumberOfProducts() {
        return driver.findElements(productItems).size();
    }
    public WishlistPage removeProduct(int i) {
        driver.findElement(By.cssSelector(".remove_from_wishlist")).click();
        waitForLoadingIcons();
        return this;
    }
}