package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobjects.CartPage;
import pageobjects.ProductPage;

public class CouponsTests extends BaseTests{
    private final String productWindSurURLSlug = "/fuerteventura-sotavento/";
    private final String productWspinFerURLSlug = "/wspinaczka-via-ferraty/";
    private final String productFuertaSlug = "fuerteventura-sotavento/";
    private final String productGranKoscSlug = "gran-koscielcow/";
    @Test
    @DisplayName("Using coupon - inactive")
    public void using_coupon_inactive(){
        String coupon = "starośćnieradość";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productWspinFerURLSlug)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon);

        Assertions.assertEquals(cartPage.getSuccessMessage(), "Ten kupon stracił ważność.",
                "Coupon message is not ok");
    }

    @Test
    @DisplayName("Using coupon - active 250 no limit/once")
    public void using_coupon_active_once(){
        String coupon = "kwotowy250";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productFuertaSlug)
                .addToCart()
                .goToCart()
                .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(), "Kupon został pomyślnie użyty." , "Coupon message is not ok");
    }
    @Test
    @DisplayName("Using coupon - active 250 no limit/twice")
    public void using_coupon_active_twice(){
        String coupon = "kwotowy250";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productGranKoscSlug)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon)
                .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(), "Kupon został zastosowany!", "ddd");
    }
    @Test
    @DisplayName("Using coupon - not exist")
    public void using_coupon_not_exist(){
        String coupon = "wrong";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productGranKoscSlug)
                .addToCart()
                .goToCart()
                .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(), "Kupon " + '"'+coupon+'"' + " nie istnieje!", " ...");

    }
}
