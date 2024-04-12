import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartTests {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "https://fakestore.testelka.pl/";

    By addToCartFromProductButtonLocator = By.cssSelector("[name=add-to-cart]");
    By goToCartFromProductButtonLocator = By.cssSelector(".woocommerce-message>.button");
    By productIsInCartLocator = By.cssSelector("tr.cart_item");
    By updateCartButtonLocator = By.cssSelector("[name=update_cart]");
    By loadingIconLocator = By.cssSelector(".blockUI");
    By quantityFieldInCartLocator = By.cssSelector("input.qty");
    By totalPriceInCartLocator = By.cssSelector(".cart-subtotal [data-title=Kwota]");
    By emptyCartInfoLocator = By.cssSelector(".entry-content .cart-empty");

    String productWindSurURL = "/product/fuerteventura-sotavento/";
    String productWspinFerURL = "/product/wspinaczka-via-ferraty/";

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
    @Test
    @DisplayName("No product added to cart should art be empty")
    public void no_product_added_to_cart_should_art_be_empty(){

        //driver.get(baseURL + productWindSurURL);
        //driver.findElement(addToCartFromProductButtonLocator).click();

        driver.get(baseURL + "/koszyk/");

        Assertions.assertEquals(0, driver.findElements(productIsInCartLocator).size(),
                "Number of products in cart is not 0");
    }
    @Test
    @DisplayName("Product added to cart should cart have one product")
    public void produc_added_to_cart_should_cart_have_one_product(){
        driver.get(baseURL + productWspinFerURL);
        driver.findElement(addToCartFromProductButtonLocator).click();

        driver.get(baseURL + "/koszyk/");

        int numberOfProducts= driver.findElements(productIsInCartLocator).size();
        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                "\n Actual number of products: " + numberOfProducts);
    }
    @Test
    @DisplayName("Two products added to cart should cart have two products")
    public void two_roducts_added_to_cart_shoul_cart_have_two_products(){
        driver.get(baseURL+productWindSurURL);
        driver.findElement(addToCartFromProductButtonLocator).click();
        driver.get(baseURL+productWspinFerURL);
        driver.findElement(addToCartFromProductButtonLocator).click();

        driver.get(baseURL + "/koszyk/");
        int numberOfProducts = driver.findElements(productIsInCartLocator).size();
        Assertions.assertEquals(2, numberOfProducts, "Expected number of products in cart: 2" +
        "\nActual number of products: " + numberOfProducts);
    }
}
