package ru.netology.Test;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.Data.DataHelper;
import ru.netology.Page.LoginPage;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import ru.netology.Page.DashBoardPage;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class MoneyTransferTest {

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
    }

    @Test
    void shouldAddMoneyToFirstCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashBoardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalance = dashBoardPage.getFirstCardBalance();
        val secondCardBalance = dashBoardPage.getSecondCardBalance();
        val transferPage = dashBoardPage.transferFirstCard();
        val sum = DataHelper.getAmountOfMoney(secondCardBalance);
        transferPage.transferMoney(sum, DataHelper.getSecondCardNumber());
        val expectedFirstCardBalance = firstCardBalance + sum;
        val expectedSecondCardBalance = secondCardBalance - sum;
        Assertions.assertEquals(expectedFirstCardBalance, DashBoardPage.getFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, DashBoardPage.getSecondCardBalance);

    }

    @Test
    void shouldAddMoneyToFirstCard() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashBoardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalance = dashBoardPage.getFirstCardBalance();
        val secondCardBalance = dashBoardPage.getSecondCardBalance();
        val transferPage = dashBoardPage.transferSecondCard();
        val sum = DataHelper.getAmountOfMoney(firstCardBalance);
        transferPage.transferMoney(sum, DataHelper.getFirstCardNumber());
        val expectedFirstCardBalance = firstCardBalance - sum;
        val expectedSecondCardBalance = secondCardBalance + sum;
        Assertions.assertEquals(expectedFirstCardBalance, DashBoardPage.getFirstCardBalance);
        Assertions.assertEquals(expectedSecondCardBalance, DashBoardPage.getSecondCardBalance);
    }

    @Test
    void shouldAddMoreMoneyThenHave() {
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        val dashBoardPage = verificationPage.validVerify(verificationCode);
        val firstCardBalance = dashBoardPage.getFirstCardBalance();
        val secondCardBalance = dashBoardPage.getSecondCardBalance();
        val transferPage = dashBoardPage.transferFirstCard();
        val sum = DataHelper.getAmountOfMoney(secondCardBalance) * 5;
        transferPage.transferMoney(sum, DataHelper.getFirstCardNumber());
        $("[data-test-id=error-notification").shouldHave(Condition.exactText("Ошибка Ошибка! Произошла ошибка"));
    }


}
