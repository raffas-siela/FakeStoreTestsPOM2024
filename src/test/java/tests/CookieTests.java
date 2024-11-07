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
        MainPage mainPage = new MainPage(browser);

        /*// Initialize the product page
        ProductPage productPage = new ProductPage(browser);
        // Navigate to the product page and add the product to the cart
        ProductPage cookiePage = productPage
                .go(productPage.product08WspinKosc)
                .closeInfoButton()
                .addToCart();*/
        // Create a new cookie with a specific expiration date
        Cookie newCookie = new Cookie("test cookie name",
                "test cookie value",
                "fakestore.testelka.pl",
                "/",
                new GregorianCalendar(2023, Calendar.AUGUST, 24).getTime(),
                true,
                true);
        //browser.driver.manage().deleteAllCookies();
        // Add the cookie to the browser
        browser.driver.manage().addCookie(newCookie);
        // Assert that the cookie has been added
        Assertions.assertEquals(12, browser.driver.manage().getCookies().size(),
                "Cookies was not add");


        // Optionally, assert that the cookie has the correct expiration date
       /* Cookie addedCookie = browser.driver.manage().getCookieNamed("test cookie name");
        Assertions.assertNotNull(addedCookie, "Cookie was not found");
        Assertions.assertEquals(new GregorianCalendar(2023, Calendar.AUGUST, 24).getTime(), addedCookie.getExpiry(),
                "Cookie expiration date is incorrect");*/
    }
}
