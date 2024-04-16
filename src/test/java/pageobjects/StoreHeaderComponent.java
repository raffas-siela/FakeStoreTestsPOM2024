package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class StoreHeaderComponent extends BasePage {
    private final By goToWishlistFromHeader = By.cssSelector("#menu-item-248");
    protected StoreHeaderComponent(WebDriver driver) {
        super(driver);
    }
    public WishlistPage goToWishlist() {
        driver.findElement(goToWishlistFromHeader).click();
        return new WishlistPage(driver);
    }
}
