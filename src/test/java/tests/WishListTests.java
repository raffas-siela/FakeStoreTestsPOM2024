package tests;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.WishlistPage;

public class WishListTests {
    WebDriver driver;
    private final String productWspinFerSlug = "/wspinaczka-via-ferraty/";

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }
    @AfterEach
    public void quitDriver(){
        driver.quit();
    }

    @Test
    @DisplayName("Product added to wishlist should wishlist has one item")
    public void productToWishlist(){
        ProductPage productPage = new ProductPage(driver)
                .go(productWspinFerSlug);
        WishlistPage wishlistPage = productPage
                .addToWishlist()
                .storeHeader
                .goToWishlist();

        Assertions.assertEquals(1, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected (1)");
    }

    @Test
    @DisplayName("No product added to wishlist should wishlist be empty")
    public void emptyWishlist(){
        MainPage mainPage = new MainPage(driver);
        WishlistPage wishlistPage = mainPage
                .go()
                .storeHeader
                .goToWishlist();

        Assertions.assertEquals(0, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected (0)");
    }
}
