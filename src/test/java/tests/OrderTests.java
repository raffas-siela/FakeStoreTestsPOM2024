package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class OrderTests extends BaseTests{

    @ParameterizedTest()
    @DisplayName("sth")
    @CsvSource({

    })
    public void order_without_account_creation(String productUrl){

    }
}
