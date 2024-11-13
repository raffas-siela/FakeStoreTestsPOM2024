package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;
import pageobjects.DropdownListPage;

import java.util.List;

public class DropdownListTests extends BaseTests{
    @Test
    @DisplayName("Single choice select")
    public void singleChoiceSelect(){
        DropdownListPage dropdownListPage = new DropdownListPage(browser);
        dropdownListPage
                .go();
        dropdownListPage.selectSingleChoice("marakuja");
        String selectedOptions = dropdownListPage.getSelectedOption();
        Assertions.assertEquals("marakuja", selectedOptions,
                "The selected otpion is not correct");
    }

    @Test
    @DisplayName("Mutiple choice select")
    public void multipleChoiceSelect(){
        DropdownListPage dropdownListPage = new DropdownListPage(browser);
        dropdownListPage
                .go();
        Select select = new Select(dropdownListPage.getDropdownMulti());
        select.deselectAll();
        dropdownListPage.selectMultipleChoice("truskawkowy", "waniliowy");
        List<String> selectedOptions = dropdownListPage.getSelectedOptions();
        Assertions.assertTrue(selectedOptions.contains("truskawkowy"),
                "The selected option 'truskawkowy' is not correct");
        Assertions.assertTrue(selectedOptions.contains("waniliowy"),
                "The selected option 'waniliowy' is not correct");
    }
    @Test
    @DisplayName("Checking number of options")
    public void checkingList(){
        DropdownListPage dropdownListPage = new DropdownListPage(browser);
        dropdownListPage.go();
        Select select = new Select(dropdownListPage.getDropdownMulti());
        Assertions.assertEquals(4, select.getOptions().size());
    }
}
