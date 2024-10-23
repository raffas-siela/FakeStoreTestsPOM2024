package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobjects.CartPage;
import pageobjects.ProductPage;

public class CartTestsParametrized extends BaseTests{
    @ParameterizedTest(name = "Adding product {0} to Card")
    @DisplayName("ParameterizedTest - One product added to cart should cart has one product")
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
    public void paramOnlyOneProductCart(String productUrl) {
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productUrl)  // Assuming go method takes productUrl as parameter
                .closeInfoButton()
                .addToCart()
                .goToCart();
        int numberOfProducts = cartPage.getNumberOfProducts();
        Assertions.assertEquals(1, numberOfProducts, "Number of products in cart is not expected");
    }
}
