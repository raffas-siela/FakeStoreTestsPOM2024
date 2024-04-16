import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends BasePage{
    private final By addToCart = By.cssSelector("[name=add-to-cart]");
    private final By goToCart = By.cssSelector(".woocommerce-message>.button");
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
}
