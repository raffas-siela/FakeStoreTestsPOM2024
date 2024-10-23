package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobjects.CartPage;
import pageobjects.ProductPage;

public class CouponTestParametrized extends BaseTests{
    @ParameterizedTest(name = "Product {0} added to Card with coupon: inactive")
    @DisplayName("Using coupon - inactive")
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
    public void using_coupon_inactive(String productUrl) {
       String coupon = "starośćnieradość";
       ProductPage productPage = new ProductPage(browser);
       CartPage cartPage = productPage
               .go(productUrl)
               .closeInfoButton()
               .addToCart()
               .goToCart()
               .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(),
                "Ten kupon stracił ważność.",
                "Coupon message is not what expected");
    }
    @ParameterizedTest(name = "Product {0} added to Card with coupon: not existing")
    @DisplayName("Using coupon - not existing")
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
    public void using_coupon_not_exist(String productUrl) {
        String coupon2 = "wrong";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productUrl)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon2);
        Assertions.assertEquals(cartPage.getSuccessMessage(),
                "Kupon " + '"'+coupon2+'"' + " nie istnieje!",
                "Coupon message is not what expected");
    }
}
