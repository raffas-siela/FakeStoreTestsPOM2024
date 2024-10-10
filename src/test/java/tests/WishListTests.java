package tests;
import org.junit.jupiter.api.*;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.WishlistPage;

public class WishListTests extends BaseTests {
    private final String productWspinFerSlug = "/wspinaczka-via-ferraty/";
    private final String productGranKoscSlug = "gran-koscielcow/";

/*    @Test
    @DisplayName("Products added to wishlist should wishlist has two items - original")
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
    }*/

    @Test
    @DisplayName("One products added to wishlist should wishlist has 1 item")
    public void product_added_to_wishlist_should_wishlist_have_one_item() {
        ProductPage productPage = new ProductPage(browser)
                .go(productWspinFerSlug)
                .closeInfoButton()
                .addToWishlist();
        WishlistPage wishlistPage = productPage
                .storeHeader
                .goToWishlist();

        Assertions.assertEquals(1, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    @DisplayName("Two products added to wishlist should wishlist has 2 items")
    public void productToWishlist(){
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Test failed due to an exception: " + e.getMessage());
        }
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
/*    @Test
    public void addAndRemoveProductFromWishlist() {
        ProductPage productPage = new ProductPage(driver)
                .goToProduct("productWspinFerSlug")
                .closeInfoButton()
                .addToWishlist()
                .goToProduct("productGranKoscSlug")
                .addToWishlist();
        WishlistPage wishlistPage = productPage
                .storeHeader()
                .goToWishlist();

        Assert.assertEquals(wishlistPage.getNumberOfProducts(), 2,
                "Number of products in wishlist should be 2");

        // Usuń produkt z listy życzeń
        wishlistPage.removeProductFromWishlist("Wspinaczka");

        Assert.assertEquals(wishlistPage.getNumberOfProducts(), 1,
                "Number of products in wishlist should be 1 after removal");
    }*/
}
