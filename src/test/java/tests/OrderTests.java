/*
package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobjects.CartPage;
import pageobjects.PaymentPage;
import pageobjects.ProductPage;

public class OrderTests extends BaseTests{

    @Test
    @DisplayName("Order without account creation")
    public void order_without_account_creation(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToCart()
                .goToCart();
        PaymentPage paymentPage = cartPage
                .goToPayment()

        Assertions.assertEquals();


    }
}
*/
