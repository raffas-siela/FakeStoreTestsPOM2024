package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import pageobjects.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CookieTests extends BaseTests{
    @Test
    @DisplayName("Adding cookie with date")
    public void addingCookieWithDate(){
        // Initialize the page
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        // Create a new cookie with a specific expiration date
        Cookie newCookie = new Cookie("test cookie name",
                "test cookie value",
                "fakestore.testelka.pl",
                "/",
                new GregorianCalendar(2023, Calendar.AUGUST, 24).getTime(),
                true,
                true);
        browser.driver.manage().addCookie(newCookie);
        // Assert that the cookie has been added
        Assertions.assertEquals(7, browser.driver.manage().getCookies().size(),
                "Cookies was not add");
    }
    @Test
    @DisplayName("Items in Cart cookie")
    public void itemsInCartCookie(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product08WspinKosc)
                .addToCart()
                .goToCart();
        Cookie itemsInCartCookie = browser.driver.manage().getCookieNamed("woocommerce_items_in_cart");
        Assertions.assertNotNull(itemsInCartCookie,
                "Cookie 'woocommerce_items_in_cart' is not exist");
        Assertions.assertEquals("1", itemsInCartCookie.getValue(),
                "Value of cookie 'woocommerce_items_in_cart' is not corect");
    }
    @Test
    @DisplayName("Removing cookie adding to Cart")
    public void removeCookieAddingToCart(){
        ProductPage productPage = new ProductPage(browser);
        CartPage cartPage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToCart()
                .goToCart();

        Cookie itemsInCartCookie = browser.driver.manage().getCookieNamed("woocommerce_items_in_cart");
        Assertions.assertNotNull(itemsInCartCookie,
                "Cookie 'woocommerce_items_in_cart' is not exist");
        Assertions.assertEquals(12, browser.driver.manage().getCookies().size(),
                "The number of cookies is lower than expected");

        browser.driver.manage().deleteCookie(itemsInCartCookie);
        Cookie itemsNoiInCartCookie = browser.driver.manage().getCookieNamed("woocommerce_items_in_cart");
        Assertions.assertNull(itemsNoiInCartCookie,
                "Cookie 'woocommerce_items_in_cart' exist");
        Assertions.assertEquals(11, browser.driver.manage().getCookies().size(),
                "The number of cookies is lower than expected");
    }
}
