package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WishlistPage extends BasePage{
    private final By productsItems = By.cssSelector(".wishlist-items-wrapper");
    protected WishlistPage(WebDriver driver) {
        super(driver);
    }

    public int getNumberOfProducts() {
        return driver.findElements(productsItems).size();
    }
}
