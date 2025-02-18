package tests;
import helpers.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.RegisterExtension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BaseTests {
    @RegisterExtension
    TestStatus status = new TestStatus();

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
    public void afterEach (TestInfo info){
        if (status.isFailed) {
            takeScreenshotsOnFail(info.getDisplayName());
        }
        browser.driver.quit();
    }

    protected double parsePrice(String price) {
        // Usunięcie spacji, symbolu waluty i zamiana przecinka na kropkę
        String cleanedPrice = price
                .replace(" ", "")
                .replace("zł", "")
                .replace(",", ".");
        return Double.parseDouble(cleanedPrice);
    }

    private void takeScreenshotsOnFail(String displayName){
        File screenshot = ((TakesScreenshot)browser.driver).getScreenshotAs(OutputType.FILE);
        String folderLocation = "src/test/java/helpers/screenshotsFail/";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH-mm-ss");
        String formattedNow = LocalDateTime.now().format(formatter);
        String destinationPath = Paths.get("src/test/java/helpers/screenshotsFail/" +
                displayName + " - " + formattedNow + ".png").toString();
        try{
            Files.createDirectories(Paths.get(folderLocation));
            Files.copy(screenshot.toPath(), Path.of(destinationPath));
            System.out.println("Screenshot saved at " + destinationPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}