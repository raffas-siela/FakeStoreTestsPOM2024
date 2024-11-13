package tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.print.PrintOptions;
import pageobjects.MainPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class PrintTest extends BaseTests{
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
        } catch (IOException e) {
            throw new RuntimeException("An error occured while writing the PDF file: " + e);
        }
    }
}
