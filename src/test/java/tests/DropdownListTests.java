package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.Select;
import pageobjects.DropdownListPage;

public class DropdownListTests extends BaseTests{
    @Test
    @DisplayName("Single choice select")
    public void singleChoiceSelect(){
        // Inicjalizacja strony z listą rozwijaną
        DropdownListPage dropdownListPage = new DropdownListPage(browser);

        // Wybór opcji z listy rozwijanej
        dropdownListPage.selectSingleChoice("passion-fruit");

        // Pobranie wybranej opcji
        String selectedOption = dropdownListPage.getSelectedOption();

        // Sprawdzenie, czy wybrana opcja jest poprawna
        Assertions.assertEquals("passion-fruit", selectedOption, "The selected option is incorrect");
    }

    @Test
    @DisplayName("Mutiple choice select")
    public void multipleChoiceSelect(){
        DropdownListPage dropdownListPage = new DropdownListPage(browser);
        dropdownListPage
                .go();
        //Select select = new Select(multipleChoiceSelect());

    }
}
