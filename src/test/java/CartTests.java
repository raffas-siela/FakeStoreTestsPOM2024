import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartTests {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "https://fakestore.testelka.pl/";


    By addToCartFromProductButtonLocator = By.cssSelector("[name=add-to-cart]");
    By goToCartFromProductButtonLocator = By.cssSelector(".woocommerce-message>.button");
    By updateCartButtonLocator = By.cssSelector("[name=update_cart]");
    By loadingIconLocator = By.cssSelector(".blockUI");
    By quantityFieldInCartLocator = By.cssSelector("input.qty");
    By totalPriceInCartLocator = By.cssSelector(".cart-subtotal [data-title=Kwota]");
    By emptyCartInfoLocator = By.cssSelector(".entry-content .cart-empty");
    By productIsInCartLocator = By.cssSelector("tr.cart_item");

    String productWindSurURL = "/product/fuerteventura-sotavento/";
    String productWindSurURLSlug = "/fuerteventura-sotavento/";
    String productWspinFerURL = "/product/wspinaczka-via-ferraty/";
    String productWspinFerURLSlug = "/wspinaczka-via-ferraty/";

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
    @DisplayName("No product added to cart should cart be emptyPOM")
    public void emptyCart(){
        CartPage cartpage = new CartPage(driver);
        cartpage.go();

        Assertions.assertEquals(0, cartpage.getNumberOfProducts(),
                "Number of products in cart is not 0");

    }
    @Test
    @DisplayName("Two product added to cart and deleted should displayed info about empty cart")
    public void emptyCartInfo(){
        driver.get(baseURL + productWspinFerURL);
        driver.findElement(addToCartFromProductButtonLocator).click();

        driver.get(baseURL + productWindSurURL);
        driver.findElement(addToCartFromProductButtonLocator).click();

        driver.findElement(goToCartFromProductButtonLocator).click();
        driver.findElement(By.cssSelector(".woocommerce-cart-form__cart-item .remove")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIconLocator, 0));

        driver.findElement(By.cssSelector(".woocommerce-cart-form__cart-item .remove")).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIconLocator, 0));

        Assertions.assertEquals("Twój koszyk jest pusty.", driver.findElement(emptyCartInfoLocator).getText(),
                "The cart is not empty");

    }
    @Test
    @DisplayName("Product added to cart should cart have one productPOM")
    public void oneProductCart(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage.go(productWindSurURLSlug).addToCart().goToCart();

        int numberOfProducts= cartPage.getNumberOfProducts();

        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                "\n Actual number of products: " + numberOfProducts);
    }
    @Test
    @DisplayName("Two products added to cart should cart have two products")
    public void twoProductsCart(){
        driver.get(baseURL+productWindSurURL);
        driver.findElement(addToCartFromProductButtonLocator).click();
        driver.get(baseURL+productWspinFerURL);
        driver.findElement(addToCartFromProductButtonLocator).click();

        driver.get(baseURL + "/koszyk/");
        int numberOfProducts = driver.findElements(productIsInCartLocator).size();
        Assertions.assertEquals(2, numberOfProducts, "Expected number of products in cart: 2" +
        "\n Actual number of products: " + numberOfProducts);
    }
    @Test
    @DisplayName("Changing quantity in cart should change total price")
    public void changingQuantityAndPrice(){
        driver.get(baseURL + productWspinFerURL);
        driver.findElement(addToCartFromProductButtonLocator).click();

        driver.get(baseURL + "/koszyk/");
        driver.findElement(quantityFieldInCartLocator).clear();
        driver.findElement(quantityFieldInCartLocator).sendKeys("2");
        driver.findElement(updateCartButtonLocator).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIconLocator, 0));

        Assertions.assertEquals("5 598,00 zł", driver.findElement(totalPriceInCartLocator).getText(),
                "Total price after quantity update is not what we expected");
    }
    @Test
    @DisplayName("Changing quantity in cart to negative should not update total price")
    public void changingQuantityNegativeAndPrice(){
        driver.get(baseURL + productWspinFerURL);
        driver.findElement(addToCartFromProductButtonLocator).click();

        driver.get(baseURL + "/koszyk/");
        driver.findElement(quantityFieldInCartLocator).clear();
        driver.findElement(quantityFieldInCartLocator).sendKeys("-3");

        driver.findElement(updateCartButtonLocator).click();

        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIconLocator, 0));

        Assertions.assertEquals("2 799,00 zł", driver.findElement(totalPriceInCartLocator).getText(),
                "total price was changed. It isn't what is expected");
    }
}
