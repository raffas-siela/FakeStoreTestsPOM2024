package pageobjects;

import helpers.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropdownListPage extends BasePage{
    //public static WebElement selectSingleChoiceList;
    WebElement selectSingleChoiceList = driver.findElement(By.cssSelector("#flavors"));

    public DropdownListPage(Browser browser) {
        super(browser);
    }
    public void go() {
        driver.get(baseURL + "/lista-rozwijana/");
    }

    public void selectSingleChoice(String value) {
        Select select = new Select(selectSingleChoiceList);
        select.selectByValue(value);
    }

    public String getSelectedOption() {
        Select select = new Select(selectSingleChoiceList);
        return select.getFirstSelectedOption().getAttribute("value");
    }

}
