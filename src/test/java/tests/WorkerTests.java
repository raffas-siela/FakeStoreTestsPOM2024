/*
package tests;
//Importy obejmują biblioteki JUnit do testowania,
// Selenium do automatyzacji przeglądarki oraz klasy do obsługi plików i kodowania Base64.
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.print.PrintOptions;
import pageobjects.MainPage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;

public class WorkerTests extends BaseTests{

    @Test
*/
/*    Ten test: Otwiera stronę główną.Drukuje stronę do formatu PDF.Dekoduje zawartość PDF z Base64.Zapisuje plik PDF na dysku.*//*

    public void printPage(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        PrintsPage printer = (PrintsPage) browser.driver;
        PrintOptions printOptions = new PrintOptions();
        Pdf pdf = printer.print(printOptions);
        String content = pdf.getContent();

        Path outputPath = Paths.get("src/test/java/helpers/prints/output.pdf");

        byte[] decodedBytes = Base64.getDecoder().decode(content);
        try {
            Files.write(outputPath, decodedBytes);
            System.out.println("Output saved.");
        } catch (IOException e) {
            throw new RuntimeException("An error occured while writing the PDF file: " + e);
        }
    }
    @Test
    //Ten test:Otwiera stronę główną.Robi zrzut ekranu całej strony.Zapisuje zrzut ekranu na dysku.
    public void ScreenshotPageTest(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        File screenshot = ((TakesScreenshot)browser.driver).getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("src/test/java/helpers/screenshotsPage/image.png");
        try {
            Files.copy(screenshot.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved");
        } catch (IOException e) {
            throw new RuntimeException("An error occured while creating screenshot file: " + e);
        }
    }
    @Test
    //Ten test:Otwiera stronę główną.Znajduje element o ID "masthead".Robi zrzut ekranu tego elementu.Zapisuje zrzut ekranu na dysku.
    public void ScreenshotElementTest(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        WebElement element = browser.driver.findElement(By.id("masthead"));

        File screenshot = element.getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("src/test/java/helpers/screenshotsElement/image.png");
        try {
            Files.copy(screenshot.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved.");
        } catch (IOException e) {
            throw new RuntimeException("An error occured while creating screenshot file: " + e);
        }
    }
}
*/
