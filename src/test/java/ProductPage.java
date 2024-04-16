import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage {
    private final WebDriver driver;
    private final By addToCart = By.cssSelector("[name=add-to-cart]");
    private final By goToCart = By.cssSelector(".woocommerce-message>.button");
    public ProductPage(WebDriver driver){
        this.driver = driver;
    }
    public ProductPage go(String productWspinFerURLSlug) {
        String baseURL = "https://fakestore.testelka.pl/";
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
