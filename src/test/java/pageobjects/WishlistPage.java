package pageobjects;
import org.openqa.selenium.By;
import helpers.Browser;

public class WishlistPage extends BasePage{
    private final By productsItems = By.cssSelector(".wishlist-items-wrapper tr td.product-remove");
     protected WishlistPage(Browser browser) {
        super(browser);
    }
    public int getNumberOfProducts() {
        return driver.findElements(productsItems).size();
    }
}
