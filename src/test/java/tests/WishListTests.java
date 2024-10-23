package tests;
import org.junit.jupiter.api.*;
import pageobjects.MainPage;
import pageobjects.ProductPage;
import pageobjects.WishlistPage;

public class WishListTests extends BaseTests {

    @Test
    @DisplayName("No products added to wishlist should have no intem")
    public void no_products_added_no_item(){
        MainPage mainPage = new MainPage(browser);
        WishlistPage wishlistPage = mainPage
                .go()
                .storeHeader
                .goToWishlist();
        Assertions.assertEquals(0, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    @DisplayName("One product added to wishlist should have one item")
    public void one_produckt_added_one_item(){
        ProductPage productPage = new ProductPage(browser);
        WishlistPage wishlistPage = productPage
                .go(productPage.product01WindSurf)
                .closeInfoButton()
                .addToWishlist()
                .goToWishlist();
        Assertions.assertEquals(1, wishlistPage.getNumberOfProducts(),
                        "Number of products in wishlist is not what expected.");
    }

    @Test
    @DisplayName("Two products added to wishlist should have two items")
    public void two_products_added_two_items(){
        ProductPage productPage = new ProductPage(browser);
        WishlistPage wishlistPage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToWishlist()
                .go(productPage.product07WspinFer)
                .addToWishlist()
                .goToWishlist();

        Assertions.assertEquals(2, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    @DisplayName("Three products added to wishlist should have three items")
    public void three_produckts_added_to_wishlist_should_have_three_items(){
        ProductPage productPage = new ProductPage(browser);
        WishlistPage wishlistPage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToWishlist()
                .go(productPage.product07WspinFer)
                .addToWishlist()
                .go(productPage.product01WindSurf)
                .addToWishlist()
                .goToWishlist();

        Assertions.assertEquals(3, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }

    @Test
    @DisplayName("Three products added to wishlist and one is removed should have two items")
    public void three_products_added_one_is_removed(){
        ProductPage productPage = new ProductPage(browser);
        WishlistPage wishlistPage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToWishlist()
                .go(productPage.product07WspinFer)
                .addToWishlist()
                .go(productPage.product01WindSurf)
                .addToWishlist()
                .goToWishlist();
        Assertions.assertTrue(wishlistPage.getNumberOfProducts() > 0, "Wishlist is empty before removing a product.");
        wishlistPage.removeProduct(0);
        Assertions.assertEquals(2, wishlistPage.getNumberOfProducts(),
                "Number of products in wishlist is not what expected.");
    }
}
