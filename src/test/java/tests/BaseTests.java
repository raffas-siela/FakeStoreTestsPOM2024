package tests;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTests {
    WebDriver driver;
    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
    }
    @AfterEach
    public void quitDriver(){
        driver.quit();
    }
}
