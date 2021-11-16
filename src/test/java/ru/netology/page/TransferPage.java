package ru.netology.page;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private SelenideElement heading = $("[data-test-id=dashboard]");

    public void transferMoney(int transferAmount, DataHelper.CardInfo cardInfo) {
        $("[data-test-id='amount'] input").setValue(String.valueOf(transferAmount));
        $("[data-test-id='from'] input").setValue(cardInfo.getCardNumber());
        $("[data-test-id='action-transfer']").click();
    }

    public void errorTransfer(){
        $("[data-test-id=error-notification").shouldHave(Condition.exactText("Ошибка Ошибка! Произошла ошибка"));
    }


}
