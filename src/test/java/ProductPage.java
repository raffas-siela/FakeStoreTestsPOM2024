import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    public Object goToCart;
    By addToCartButtonLocator = By.cssSelector("[name=add-to-cart]");
    By goToCartButtonLocator = By.cssSelector(".woocommerce-message>.button");
    private final WebDriver driver;
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }
    public ProductPage go(String productWspinFerURLSlug) {
        String baseURL = "https://fakestore.testelka.pl/";
        driver.get(baseURL + "/product/" + productWspinFerURLSlug);
        return this;
    }

    public ProductPage addToCart() {
        driver.findElement(addToCartButtonLocator).click();
        return this;
    }

    public CartPage goToCart() {
        driver.findElement(goToCartButtonLocator).click();
        return new CartPage(driver);
    }
}
