import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartTests {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "https://fakestore.testelka.pl/";

    String productWindSurURLSlug = "/fuerteventura-sotavento/";
    String productWspinFerURL = "/product/wspinaczka-via-ferraty/";
    String productWspinFerURLSlug = "/wspinaczka-via-ferraty/";

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }
    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
    @Test
    @DisplayName("No product added to cart should cart be empty_POM_Testelka")
    public void emptyCart(){
        CartPage cartpage = new CartPage(driver);
        cartpage.go();

        Assertions.assertEquals(0, cartpage.getNumberOfProducts(),
                "Number of products in cart is not 0");
    }

    @Test
    @DisplayName("Two product added to cart and deleted should displayed info about empty cart")
    public void emptyCartInfo(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productWspinFerURLSlug)
                .addToCart()
                .go(productWindSurURLSlug)
                .addToCart()
                .goToCart()
                .removeFromCart()
                .removeFromCart();

        Assertions.assertEquals("Twój koszyk jest pusty.", cartPage.getInfoEmpty(),
                "The cart is not empty");
    }

    @Test
    @DisplayName("Product added to cart should cart have one product_POM_Testelka")
    public void oneProductCart(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productWindSurURLSlug)
                .addToCart()
                .goToCart();

        int numberOfProducts= cartPage.getNumberOfProducts();

        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 1" +
                "\n Actual number of products: " + numberOfProducts);
    }

    @Test
    @DisplayName("One product added to cart should cart have one products_POM_Testelka")
    public void oneProductsCart(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productWspinFerURLSlug)
                .addToCart()
                .goToCart();

    int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(1, numberOfProducts,
                "Expected number of products in cart: 2" +
                "\n Actual number of products: " + numberOfProducts);
    }

    @Test
    @DisplayName("Two products added to cart should cart have two products_POM_Testelka")
    public void twoProductsCart(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productWindSurURLSlug)
                .addToCart()
                .go(productWspinFerURLSlug)
                .addToCart()
                .goToCart();

        int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(2, numberOfProducts,
                "Expected number of products in cart: 2" +
                "\n Actual number of products: " + numberOfProducts);
    }

    @Test
    @DisplayName("Changing quantity in cart should change total price_POM_Testelka")
    public void changingQuantityAndPrice(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productWspinFerURL)
                .addToCart()
                .goToCart()
                .changeQuantity(2);

        Assertions.assertEquals("5 598,00 zł", cartPage.getTotalPrice(),
                "Total price after quantity update is not what we expected");
    }

    @Test
    @DisplayName("Changing quantity in cart to negative should not update total price_testelka")
    public void changingQuantityNegativeAndPrice(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productWspinFerURLSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(-3);

        Assertions.assertEquals("2 799,00 zł", cartPage.getTotalPrice(),
                "total price was changed. It isn't what is expected");
    }
}
