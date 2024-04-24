package pageobjects;
import org.openqa.selenium.By;
import helpers.Browser;

public class WishlistPage extends BasePage{
    private final By productsItems = By.cssSelector(".wishlist-items-wrapper .product-thumbnail");
     protected WishlistPage(Browser browser) {
        super(browser);
    }
    public int getNumberOfProducts() {
        return driver.findElements(productsItems).size();
    }
}
