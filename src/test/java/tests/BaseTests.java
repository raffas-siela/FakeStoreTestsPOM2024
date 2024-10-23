package tests;
import helpers.Browser;
import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import pageobjects.CartPage;
import pageobjects.ProductPage;

public class BaseTests {
    protected Browser browser;
    private static ConfigurationReader configuration;

    public CartPage addProductToCard (String productUrl){
        ProductPage productPage = new ProductPage(browser);
        return productPage
                .go(productUrl)
                .closeInfoButton()
                .addToCart()
                .goToCart();
    }

    @BeforeAll
    public static void loadConfiguration(){
        configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup(){
        BrowserFactory browserFactory = new BrowserFactory();
        try {
            browser = browserFactory.createInstance(configuration);
        } catch (NoSuchBrowserException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void quitDriver(){
        browser.driver.quit();
    }
    protected double parsePrice(String price) {
        // Usunięcie spacji, symbolu waluty i zamiana przecinka na kropkę
        String cleanedPrice = price.replace(" ", "").replace("zł", "").replace(",", ".");
        return Double.parseDouble(cleanedPrice);
    }
}
