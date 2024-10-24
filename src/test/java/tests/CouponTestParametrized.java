package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pageobjects.CartPage;
import pageobjects.ProductPage;

//kwotowy250 – kupon na 250 zł. bez ograniczeń - 1
//kwotowy250pojedynczy – jak wyżej, ale nie może zostać użyty z innymi kuponami - 1
//10procent – kupon na 10% wartości koszyka bez ograniczeń
//kwotowy300 – kupon na 300 zł. dla minimalnej wartości koszyka 3000 zł.
//kwotowy300bezpromocji – kupon na 300 zł. nie obejmujący produktów na promocji
//10procent1 – kupon na 10% wartości koszyka możliwy do wykorzystania raz przez każdego użytkownika
//windsurfing350 – kupon obniżający wartość produktu z kategorii windsurfing o 350 zł.
//starośćnieradość – kupon, który już wygasł i nie powinien zadziałać
//wrong - kupon nieistniejący - 1


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
       String coupon01 = "starośćnieradość";
       ProductPage productPage = new ProductPage(browser);
       CartPage cartPage = productPage
               .go(productUrl)
               .closeInfoButton()
               .addToCart()
               .goToCart()
               .applyCoupon(coupon01);
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
        String coupon01 = "wrong";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productUrl)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon01);
        Assertions.assertEquals(cartPage.getSuccessMessage(),
                "Kupon " + '"'+coupon01+'"' + " nie istnieje!",
                "Coupon message is not what expected");
    }
    @ParameterizedTest(name = "Product {0} added to Card with coupon: 250nolimit ")
    @DisplayName("Using coupon - 250 no limit")
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
    public void using_coupon_250_no_limits(String productUrl) {
        String coupon01 = "kwotowy250";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productUrl)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon01);
        Assertions.assertEquals(cartPage.getSuccessMessage(),
                "Kupon został pomyślnie użyty.",
                "Coupon massage is not what expected");
    }
    @ParameterizedTest(name = "Product {0} added to Card with coupons: 250nolimit + 10%")
    @DisplayName("Using coupon - 250nolimit + 10%")
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
    public void using_coupon_250_no_limits_with_10_percent(String productUrl){
        String coupon01 = "kwotowy250";
        String coupon02 = "10procent";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productUrl)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon01)
                .applyCoupon(coupon02);
        Assertions.assertEquals(cartPage.getSuccessMessage(),
                "Kupon został pomyślnie użyty.",
                "Coupon message is not what expected");
    }
    @ParameterizedTest(name = "Product {0} was added t0 Cart with coupons: 250limit + 10%")
    @DisplayName("Using coupon - 250limit + 10%")
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
    public void sing_coupon_250_limit_with_10_percent(String productUrl){
        String coupon01 = "10procent";
        String coupon02 = "kwotowy250pojedynczy";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productUrl)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon02)
                .applyCoupon(coupon01);
        Assertions.assertEquals(cartPage.getSuccessMessage(),
                "Przepraszamy, kupon \"kwotowy250pojedynczy\" " +
                        "został już użyty nie może być powiązany z innymi kuponami.",
                "Coupon message is not what expected");
    }
}
