package tests;
import helpers.ConfigurationReader;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BaseTests {
    protected WebDriver driver;
    private static ConfigurationReader configuration;
    @BeforeAll
    public static void loadConfiguration(){
        configuration = new ConfigurationReader();
    }
    @BeforeEach
    public void setup(){
        String browser = configuration.getBrowser();
        boolean isHeadless = configuration.isHeadless();
        switch (browser) {
            case "firefox" -> driver = new FirefoxDriver();
            case "chrome" -> driver = new ChromeDriver();
            case "edge" -> driver = new EdgeDriver();
        }
    }
    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
}
