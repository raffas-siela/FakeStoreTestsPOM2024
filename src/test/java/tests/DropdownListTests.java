/*
package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;
import pageobjects.DropdownListPage;

import java.util.List;

public class DropdownListTests extends BaseTests{
    @Test
    //Ten test: Otwiera stronę z listą rozwijaną.Wybiera opcję "marakuja" z listy rozwijanej.Sprawdza, czy wybrana opcja jest poprawna.
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
    //Ten test:Otwiera stronę z listą rozwijaną.Odznacza wszystkie opcje.Wybiera opcje "truskawkowy" i "waniliowy" z listy rozwijanej.Sprawdza, czy wybrane opcje są poprawne.
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
    //Ten test:twiera stronę z listą rozwijaną.Sprawdza, czy liczba opcji w liście rozwijanej jest równa 4.
    @DisplayName("Checking number of options")
    public void checkingList(){
        DropdownListPage dropdownListPage = new DropdownListPage(browser);
        dropdownListPage.go();
        Select select = new Select(dropdownListPage.getDropdownMulti());
        Assertions.assertEquals(4, select.getOptions().size());
    }
}
*/
