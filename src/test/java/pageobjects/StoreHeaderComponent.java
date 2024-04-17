package pageobjects;
import org.openqa.selenium.By;
import helpers.Browser;

public class StoreHeaderComponent extends BasePage {
    private final By goToWishlistFromHeader = By.cssSelector("#menu-item-248");
    protected StoreHeaderComponent(Browser browser) {
        super(browser);
    }
    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(browser);
    }
}
