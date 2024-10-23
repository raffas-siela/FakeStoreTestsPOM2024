package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pageobjects.CartPage;
import pageobjects.PaymentPage;
import pageobjects.ProductPage;

public class CartTests extends BaseTests{
    @Test
    @DisplayName("No product added to cart should cart be empty")
    public void emptyCart(){
        CartPage cartpage = new CartPage(browser);
        cartpage.go();
        int numberOfProducts = cartpage.getNumberOfProducts();

        Assertions.assertEquals(0, numberOfProducts,
                "Number of products in cart is not 0");
    }

    @Test
    @DisplayName("One product added to cart should cart has one product")
    public void onlyOneProductCart(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product01WindSurf)
                .addToCart()
                .goToCart();
        int numberOfProducts = cartPage.getNumberOfProducts();
        Assertions.assertEquals(1, numberOfProducts, "Number of produts in cart is not expected");
    }

    @Test
    @DisplayName("Two products added to cart should cart have two products")
    public void twoProductsCart(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product02WindEgipt)
                .addToCart()
                .go(productPage.product03WindGrecja)
                .addToCart()
                .goToCart();

        int numberOfProducts = cartPage.getNumberOfProducts();

        Assertions.assertEquals(2, numberOfProducts,
                "Expected number of products in cart: 2" +
                "\n Actual number of products: " + numberOfProducts);
    }

    @Test
    @DisplayName("Two products added to cart and deleted should displayed info about empty cart")
    public void emptyCartInfo(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product04WindKarpathos)
                .addToCart()
                .go(productPage.product05WindLanzarote)
                .addToCart()
                .goToCart()
                .removeFromCart()
                .removeFromCart();

        Assertions.assertEquals("Twój koszyk jest pusty.", cartPage.getInfoEmpty(),
                "The cart is not empty");
    }

    @Test
    @DisplayName("Three products added to cart should cart has 3 products")
    public void threeProductsCart(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product06WindZiel)
                .addToCart()
                .go(productPage.product07WspinFer)
                .addToCart()
                .go(productPage.product08WspinKosc)
                .addToCart()
                .goToCart();
        int numberOfProducts = cartPage.getNumberOfProducts();
        Assertions.assertEquals(3, numberOfProducts,
                "Number of products in cart IS NOT expected."+
                        "\n Expected number = 3"+
                        "\n actual number: " + numberOfProducts);
    }

    @Test
    @DisplayName("Changing quantity in cart should change total price")
    public void changingQuantityAndPrice(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product09WspinPeak)
                .addToCart()
                .goToCart()
                .changeQuantity(2);

        Assertions.assertEquals("16 400,00 zł", cartPage.getTotalPrice(),
                "Total price after quantity update is not what we expected");
    }

    @Test
    @DisplayName("Changing quantity in cart to negative should not update total price")
    public void changingQuantityNegativeAndPrice(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product10YogaWis)
                .closeInfoButton()
                .addToCart()
                .goToCart()
                .changeQuantity(-3);

        Assertions.assertEquals("4 299,00 zł", cartPage.getTotalPrice(),
                "total price was changed. It isn't what is expected");
    }

    @Test
    @DisplayName("Adding and increasing number of products should change product price")
    public void addingAndIncreasingChangeProductPrice() {
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product11YogaTos)
                .addToCart()
                .goToCart()
                .changeQuantity(2);

        Assertions.assertEquals("9 000,00 zł", cartPage.getTotalPrice(),
                "Total price is not correct");
    }

    @Test
    @DisplayName("Cart changed should update button enabled")
    public void cartChangedUupdateButtonEnabled(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product12YogaHis)
                .addToCart()
                .goToCart()
                .changeQuantityWithoutRefresh(2);

        Assertions.assertTrue(cartPage.updateButtonIsEnabled(),
                "Update button isn't enabled while it should. There are changes in cart");
    }

    @Test
    @DisplayName("Cart not changed should update button disabled")
    public void cart_not_changed_should_update_button_disabled(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product13YogaPort)
                .addToCart()
                .goToCart();

        Assertions.assertFalse(cartPage.updateButtonIsEnabled(),
                "Update button is enabled while it shouldn't. There are no changes in cart");
    }

    @Test
    @DisplayName("Adding product and go to payment checking by content")
    public void add_to_cart_and_pay_cont(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product14YogaMal)
                .closeInfoButton()
                .addToCart()
                .go(productPage.product15ZeglKurs)
                .addToCart()
                .go(productPage.product13YogaPort)
                .addToCart()
                .goToCart();
        PaymentPage paymentPage = cartPage
                .goToPayment();
        Assertions.assertDoesNotThrow(() -> paymentPage.isOnPaymentPage(),
                "User is not on the 'Zamówienie' page");
    }

    @Test
    @DisplayName("Adding product and go to payment checking by URL")
    public void add_to_cart_and_pay_url(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product07WspinFer)
                .closeInfoButton()
                .addToCart()
                .goToCart();
        PaymentPage paymentPage = cartPage
                .goToPayment();

        Assertions.assertEquals(paymentPage.currentUrl, browser.baseURL + "zamowienie/",
                "URL address of this page is not what expected");
    }
    }
