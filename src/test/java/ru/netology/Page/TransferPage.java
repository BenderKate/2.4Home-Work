package ru.netology.Page;
import com.codeborne.selenide.SelenideElement;
import ru.netology.Data.DataHelper;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");

    public void transferMoney (int transferAmount, DataHelper.CardInfo cardInfo){
        $("[data-test-id='amount'] .input__control").setValue(String.valueOf(transferAmount));
        $("[data-test-id=from]").setValue(cardInfo.getCardNumber());
        $("[data-test-id=action-transfer]").click();
    }
}
