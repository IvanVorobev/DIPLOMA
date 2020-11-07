package ru.netology.tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLUtils;
import ru.netology.pages.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open(System.getProperty("sut.url"));
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        SQLUtils.cleanTables();
    }

    @Test
    void shouldPayByApprovedDebitCard() {
        val paymentPage = mainPage.payByDebitCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.sucsessNotification();
        val paymentStatus = SQLUtils.getPaymentStatus();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPayByDeclinedDebitCard() {
        val paymentPage = mainPage.payByDebitCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.errorNotification();
        val paymentStatus = SQLUtils.getPaymentStatus();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPayByApprovedCreditCard() {
        val paymentPage = mainPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.sucsessNotification();
        val creditRequestStatus = SQLUtils.getCreditRequestStatus();
        assertEquals("APPROVED", creditRequestStatus);
    }

    @Test
    void shouldPayByDeclinedCreditCard() {
        val paymentPage = mainPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.errorNotification();
        val creditRequestStatus = SQLUtils.getCreditRequestStatus();
        assertEquals("DECLINED", creditRequestStatus);
    }

    @Test
    void shouldPayByDebitCardWithInvalidNumber() {
        val paymentPage = mainPage.payByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.invalidCardNumber();
    }

    @Test
    void shouldPayByCreditCardWithInvalidNumber() {
        val paymentPage = mainPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.invalidCardNumber();
    }

    @Test
    void shouldPayByDebitCardWithExpiredYear() {
        val paymentPage = mainPage.payByDebitCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.expiredYear();
    }

    @Test
    void shouldPayByCreditCardWithExpiredYear() {
        val paymentPage = mainPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.expiredYear();
    }

    @Test
    void shouldPayByDebitCardWithInvalidExpirationDate() {
        val paymentPage = mainPage.payByDebitCard();
        val invalidExpirationDateCardInformation = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDateCardInformation);
        paymentPage.invalidExpirationDate();
    }

    @Test
    void shouldPayByCreditCardWithInvalidExpirationDate() {
        val paymentPage = mainPage.payByCreditCard();
        val invalidExpirationDateCardInformation = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDateCardInformation);
        paymentPage.invalidExpirationDate();
    }

    @Test
    void shouldPayByDebitCardWithExpiredMonth() {
        val paymentPage = mainPage.payByDebitCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonthCardInformation);
        paymentPage.expiredMonth();
    }

    @Test
    void shouldPayByCreditCardWithExpiredMonth() {
        val paymentPage = mainPage.payByCreditCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonthCardInformation);
        paymentPage.expiredMonth();
    }

    @Test
    void shouldPayByDebitCardWithEmptyCardInformation() {
        val paymentPage = mainPage.payByDebitCard();
        val emptyCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardInfo(emptyCardInformation);
        paymentPage.emptyCardNumber();
        paymentPage.emptyMonth();
        paymentPage.emptyYear();
        paymentPage.emptyOwner();
        paymentPage.emptyCvc();
    }

    @Test
    void shouldPayByCreditCardWithEmptyCardInformation() {
        val paymentPage = mainPage.payByCreditCard();
        val emptyCardInformation = DataHelper.getEmptyCardInformation();
        paymentPage.enterCardInfo(emptyCardInformation);
        paymentPage.emptyCardNumber();
        paymentPage.emptyMonth();
        paymentPage.emptyYear();
        paymentPage.emptyOwner();
        paymentPage.emptyCvc();
    }

    @Test
    void shouldPayByDebitCardWithValidCardNumberAndInvalidOtherFields() {
        val paymentPage = mainPage.payByDebitCard();
        val validCardNumberWithInvalidOtherFields = DataHelper.getValidCardNumberWithInvalidOtherFields();
        paymentPage.enterCardInfo(validCardNumberWithInvalidOtherFields);
        paymentPage.invalidMonth();
        paymentPage.invalidYear();
        paymentPage.invalidOwner();
        paymentPage.invalidCvc();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidOtherFields() {
        val paymentPage = mainPage.payByCreditCard();
        val validCardNumberWithInvalidOtherFields = DataHelper.getValidCardNumberWithInvalidOtherFields();
        paymentPage.enterCardInfo(validCardNumberWithInvalidOtherFields);
        paymentPage.invalidMonth();
        paymentPage.invalidYear();
        paymentPage.invalidOwner();
        paymentPage.invalidCvc();
    }
}



