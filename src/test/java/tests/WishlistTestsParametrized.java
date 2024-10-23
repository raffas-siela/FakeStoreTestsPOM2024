package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobjects.ProductPage;
import pageobjects.WishlistPage;

public class WishlistTestsParametrized extends BaseTests{
    @ParameterizedTest(name = "Adding product {0} to Wishlist")
    @DisplayName("ParameterizedTest - One product added to Wishlist should has one product")
    @CsvSource({
            "/fuerteventura-sotavento/",
            "/egipt-el-gouna/",
            "/grecja-limnos/",
            "/windsurfing-w-karpathos/",
            "/windsurfing-w-lanzarote-costa-teguise/",
            "/wyspy-zielonego-przyladka-sal/",
            "/wspinaczka-via-ferraty/",
            "/gran-koscielcow/",
            "/wspinaczka-island-peak/",
            "/wakacje-z-yoga-w-kraju-kwitnacej-wisni/",
            "/wczasy-relaksacyjne-z-yoga-w-toskanii/",
            "/yoga-i-pilates-w-hiszpanii/",
            "/yoga-i-pilates-w-portugalii/",
            "/zmien-swoja-sylwetke-yoga-na-malcie/",
            "/kurs-zeglarski-na-mazurach/",
    })
    public void paramOnlyOneProductToWishlist(String productUrl){
        ProductPage productPage = new ProductPage(browser);
        WishlistPage wishlistPage = productPage
                .go(productUrl)
                .closeInfoButton()
                .addToWishlist()
                .goToWishlist();
        Assertions.assertEquals(wishlistPage.getNumberOfProducts(), 1,
                "Number of products in Wishlist is not what is expected");

    }
}
