import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected final WebDriver driver;
    protected BasePage (WebDriver driver){ this.driver = driver; }
    protected final String baseURL = "https://fakestore.testelka.pl/";
}
