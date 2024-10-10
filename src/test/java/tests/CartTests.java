package tests;
import org.junit.jupiter.api.*;
import pageobjects.CartPage;
import pageobjects.ProductPage;

public class CartTests extends BaseTests{
    private final String productWindSurURLSlug = "/fuerteventura-sotavento/";
    private final String productWspinFerURLSlug = "/wspinaczka-via-ferraty/";
    private final String productFuertaSlug = "fuerteventura-sotavento/";
    private final String productGranKoscSlug = "gran-koscielcow/";

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
                .go(productFuertaSlug)
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
                .go(productWindSurURLSlug)
                .addToCart()
                .go(productWspinFerURLSlug)
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
                .go(productWspinFerURLSlug)
                .addToCart()
                .go(productWindSurURLSlug)
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
                .go(productWindSurURLSlug)
                .addToCart()
                .go(productGranKoscSlug)
                .addToCart()
                .go(productWspinFerURLSlug)
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
                .go(productWspinFerURLSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(2);

        Assertions.assertEquals("5 598,00 zł", cartPage.getTotalPrice(),
                "Total price after quantity update is not what we expected");
    }
    @Test
    @DisplayName("Changing quantity in cart to negative should not update total price")
    public void changingQuantityNegativeAndPrice(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productWspinFerURLSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(-3);

        Assertions.assertEquals("2 799,00 zł", cartPage.getTotalPrice(),
                "total price was changed. It isn't what is expected");
    }
    @Test
    @DisplayName("Adding and increasing number of products should change product price")
    public void addingAndIncreasingChangeProductPrice() {
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productFuertaSlug)
                .addToCart()
                .goToCart()
                .changeQuantity(2);

        Assertions.assertEquals("7 200,00 zł", cartPage.getTotalPrice(),
                "Total price is not correct");
    }
    @Test
    @DisplayName("Cart changed should update button enabled")
    public void cartChangedUupdateButtonEnabled(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productGranKoscSlug)
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
                .go(productGranKoscSlug)
                .addToCart()
                .goToCart();

        Assertions.assertFalse(cartPage.updateButtonIsEnabled(),
                "Update button is enabled while it shouldn't. There are no changes in cart");
    }
}
