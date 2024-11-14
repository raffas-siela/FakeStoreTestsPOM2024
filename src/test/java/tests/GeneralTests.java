package tests;

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

public class GeneralTests extends BaseTests{
    @Test
    public void printPage(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        PrintsPage printer = (PrintsPage) browser.driver;
        PrintOptions printOptions = new PrintOptions();
        Pdf pdf = printer.print(printOptions);
        String content = pdf.getContent();

        Path outputPath = Paths.get("target/output.pdf");

        byte[] decodedBytes = Base64.getDecoder().decode(content);
        try {
            Files.write(outputPath, decodedBytes);
            System.out.println("Output saved.");
        } catch (IOException e) {
            throw new RuntimeException("An error occured while writing the PDF file: " + e);
        }
    }
    @Test
    public void ScreenshotPageTest(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        File screenshot = ((TakesScreenshot)browser.driver).getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("target/image.png");
        try {
            Files.copy(screenshot.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved.");
        } catch (IOException e) {
            throw new RuntimeException("An error occured while creating screenshot file: " + e);
        }
    }
    @Test
    public void ScreenshotElementTest(){
        MainPage mainPage = new MainPage(browser);
        mainPage.go();
        WebElement element = browser.driver.findElement(By.id("masthead"));

        File screenshot = element.getScreenshotAs(OutputType.FILE);
        Path destinationPath = Paths.get("target/image.png");
        try {
            Files.copy(screenshot.toPath(), destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Screenshot saved.");
        } catch (IOException e) {
            throw new RuntimeException("An error occured while creating screenshot file: " + e);
        }
    }
}
