package pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    private final By loadingIcon = By.cssSelector(".blockUI");
    protected final WebDriver driver;
    protected BasePage (WebDriver driver){ this.driver = driver; }


    protected void waitForLoadingIcons(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.numberOfElementsToBe(loadingIcon, 0));
    }

    protected final String baseURL = "https://fakestore.testelka.pl/";


}
