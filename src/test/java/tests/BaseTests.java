package tests;
import helpers.Browser;
import helpers.BrowserFactory;
import helpers.ConfigurationReader;
import helpers.NoSuchBrowserException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseTests {
    protected static Browser browser;
    private static ConfigurationReader configuration;
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