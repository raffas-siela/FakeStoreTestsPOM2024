package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobjects.CartPage;
import pageobjects.OrderPage;
import pageobjects.ProductPage;

public class OrderTests extends BaseTests {
    private final String productWindSurURLSlug = "/fuerteventura-sotavento/";
    private final String productWspinFerURLSlug = "/wspinaczka-via-ferraty/";
    private final String productFuertaSlug = "fuerteventura-sotavento/";
    private final String productGranKoscSlug = "gran-koscielcow/";

    @Test
    @DisplayName("Successful order with account creation")
    public void orderWitchNewAccount() {

        ProductPage productPage = new ProductPage(browser)
                .go(productFuertaSlug);
        CartPage cartPage = productPage.addToCart().goToCart();
        cartPage.goToPayment();
        OrderPage orderPage = new OrderPage().orderWithAccount().


        //CartPage cartPage = ca new CartPage().goToPayment();
    }
}