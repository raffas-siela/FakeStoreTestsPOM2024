package tests;
import org.junit.jupiter.api.*;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.WishlistPage;

public class WishListTests extends BaseTests {
    private final String productWspinFerSlug = "/wspinaczka-via-ferraty/";
    private final String productGranKoscSlug = "gran-koscielcow/";

    @Test
    @DisplayName("Products added to wishlist should wishlist has two items")
    public void productToWishlist(){
        ProductPage productPage = new ProductPage(browser)
                .go(productWspinFerSlug)
                .closeInfoButton()
                .addToWishlist()
                .go(productGranKoscSlug)
                .addToWishlist();
        WishlistPage wishlistPage = productPage
                .storeHeader
                .goToWishlist();

        Assertions.assertEquals(2, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected (1)");
    }

    @Test
    @DisplayName("No product added to wishlist should wishlist be empty")
    public void emptyWishlist(){
        MainPage mainPage = new MainPage(browser);
        WishlistPage wishlistPage = mainPage

                .go()
                .storeHeader
                .goToWishlist();

        Assertions.assertEquals(0, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected (0)");
    }
}
