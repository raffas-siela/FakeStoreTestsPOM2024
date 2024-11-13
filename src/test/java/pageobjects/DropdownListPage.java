package pageobjects;

import helpers.Browser;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.stream.Collectors;

public class DropdownListPage extends BasePage {
    public DropdownListPage(Browser browser) {
        super(browser);
    }
    public void go() {
        driver.get(baseURL + "/lista-rozwijana/");
    }

    public final By dropdownSingle = By.id("flavors");
    public final By dropdownMulti = By.id("flavors-multiple-selected");
    public void selectSingleChoice(String option) {
        WebElement dropdownElement = driver.findElement(dropdownSingle);
        Select select = new Select(dropdownElement);
        select.selectByVisibleText(option);
    }

    public String getSelectedOption() {
        WebElement dropdownElement = driver.findElement(dropdownSingle);
        Select select = new Select(dropdownElement);
        return select.getFirstSelectedOption().getText();
    }


    public void selectMultipleChoice(String... options) {
        WebElement dropdownElement = driver.findElement(dropdownMulti);
        Select select = new Select(dropdownElement);
        for (String option : options) {
            select.selectByVisibleText(option);
        }
    }

    public List<String> getSelectedOptions() {
        WebElement dropdownElement = driver.findElement(dropdownMulti);
        Select select = new Select(dropdownElement);
        return select.getAllSelectedOptions().stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
    public WebElement getDropdownMulti() {
        return driver.findElement(dropdownMulti);
    }
}
