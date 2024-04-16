import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartTests {
    WebDriver driver;
    WebDriverWait wait;
    String baseURL = "https://fakestore.testelka.pl/";

    private final String productWindSurURLSlug = "/fuerteventura-sotavento/";
    private final String productWspinFerURLSlug = "/wspinaczka-via-ferraty/";
    private final String productFuertaSlug = "fuerteventura-sotavento/";
    private final String granKoscSlug = "gran-koscielcow/";


    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }
    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
    @Test
    @DisplayName("No product added to cart should cart be empty")
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
    @DisplayName("Product added to cart should cart have one product")
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
    @DisplayName("One product added to cart should cart have one products")
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
    @DisplayName("Two products added to cart should cart have two products")
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
    @DisplayName("Changing quantity in cart should change total price")
    public void changingQuantityAndPrice(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productWspinFerURLSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(2);

        Assertions.assertEquals("5 598,00 zł", cartPage.getTotalPrice(),
                "Total price after quantity update is not what we expected");
    }

    @Test
    @DisplayName("Changing quantity in cart to negative should not update total price")
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

    @Test
    @DisplayName("Adding and increasing number of products should change product price")
    public void addingAndIncreasingChangeProductPrice() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(productFuertaSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(2);

        Assertions.assertEquals("7 200,00 zł", cartPage.getTotalPrice(),
                "Total price is not correct");
    }

    @Test
    @DisplayName("Cart changed should update button enabled")
    public void cartChangedUupdateButtonEnabled(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(granKoscSlug)
                .addToCart()
                .goToCart()
                .changeQuantityWithoutRefresh(2);

        Assertions.assertTrue(cartPage.updateButtonIsEnabled(),
                "Update button isn't enabled while it should. There are changes in cart");
    }
    @Test
    @DisplayName("Cart not changed should update button disabled")
    public void cart_not_changed_should_update_button_disabled(){
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = productPage
                .go(granKoscSlug)
                .addToCart()
                .goToCart();

        Assertions.assertFalse(cartPage.updateButtonIsEnabled(),
                "Update button is enabled while it shouldn't. There are no changes in cart");
    }
}
