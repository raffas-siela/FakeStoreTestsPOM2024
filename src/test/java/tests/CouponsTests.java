package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobjects.CartPage;
import pageobjects.ProductPage;

public class CouponsTests extends BaseTests{
    private double parsePrice(String price) {
        // Usunięcie spacji, symbolu waluty i zamiana przecinka na kropkę
        String cleanedPrice = price.replace(" ", "").replace("zł", "").replace(",", ".");
        return Double.parseDouble(cleanedPrice);
    }

    @Test
    @DisplayName("Using coupon - inactive")
    public void using_coupon_inactive(){
        String coupon = "starośćnieradość";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product07WspinFer)
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
                .go(productPage.product01WindSurf)
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
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon)
                .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(), "Kupon został zastosowany!", "Coupon message is not ok");
    }
    @Test
    @DisplayName("Using coupon - not exist")
    public void using_coupon_not_exist(){
        String coupon = "wrong";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product08WspinKosc)
                .addToCart()
                .goToCart()
                .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(), "Kupon " + '"'+coupon+'"' + " nie istnieje!",
                "Coupon message is not what expected");
    }
    @Test
    @DisplayName("Using coupon - 10% - Calculating value of promotion")
    public void coupon_10_value(){
        String coupon = "10procent";
        double couponValue = 0.10;
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToCart()
                .goToCart();
        // Pobranie ceny przed kuponem
        double originalPrice = parsePrice(cartPage.getTotalPrice());
        // Zastosowanie kuponu
        cartPage.applyCoupon(coupon);
        // Pobranie ceny po kuponie
        double discountedPrice = parsePrice(cartPage.getTotalPriceCoupon());
        // cena po rabacie
        double expectedPrice = originalPrice * (1 - couponValue);
        // Porównanie oczekiwanej ceny z rzeczywistą ceną po rabacie
        Assertions.assertEquals(expectedPrice, discountedPrice, 0.01, "Discounted price is not as expected.");
    }

    @Test
    @DisplayName("Using coupon - positive - for cart value above 3 thousands")
    public void using_coupon_three_thousands_positive(){
        String coupon = "kwotowy300";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product01WindSurf)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(), "Kupon został pomyślnie użyty.",
                "Coupon message is not what expected");
    }
    @Test
    @DisplayName("Using coupon - negative - for cart value above 3 thousands")
    public void using_coupon_three_thousands_negative(){
        String coupon = "kwotowy300";
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .applyCoupon(coupon);
        Assertions.assertEquals(cartPage.getSuccessMessage(),
                "Minimalna wartość zamówienia dla tego kuponu to 3 000,00 zł.",
                "Coupon message is not what expected");
    }
}
