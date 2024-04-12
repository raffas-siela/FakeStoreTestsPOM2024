import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {
    private final WebDriver driver;
    By productIsInCartLocator = By.cssSelector("tr.cart_item");
    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public void go() {
        String baseURL = "https://fakestore.testelka.pl/";
        driver.get(baseURL + "/koszyk/");
    }

    public int getNumberOfProducts() {
        return driver.findElements(productIsInCartLocator).size();
    }
}
