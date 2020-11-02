package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLUtils;
import ru.netology.page.MainPage;

import java.io.IOException;
import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServiceTest {
    MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080");
    }

    @Test
    void shouldPayByApprovedDebitCard() throws SQLException, IOException {
        val paymentPage = mainPage.payByDebitCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.sucsessNotification();
        val paymentStatus = SQLUtils.getPaymentStatus();
        assertEquals("APPROVED", paymentStatus);
    }

    @Test
    void shouldPayByDeclinedDebitCard() throws SQLException, IOException {
        val paymentPage = mainPage.payByDebitCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.errorNotification();
        val paymentStatus = SQLUtils.getPaymentStatus();
        assertEquals("DECLINED", paymentStatus);
    }

    @Test
    void shouldPayByApprovedCreditCard() throws SQLException, IOException {
        val paymentPage = mainPage.payByCreditCard();
        val approvedCardInformation = DataHelper.getApprovedCardInformation();
        paymentPage.enterCardInfo(approvedCardInformation);
        paymentPage.sucsessNotification();
        val creditRequestStatus = SQLUtils.getCreditRequestStatus();
        assertEquals("APPROVED", creditRequestStatus);
    }

    @Test
    void shouldPayByDeclinedCreditCard() throws SQLException, IOException {
        val paymentPage = mainPage.payByCreditCard();
        val declinedCardInformation = DataHelper.getDeclinedCardInformation();
        paymentPage.enterCardInfo(declinedCardInformation);
        paymentPage.errorNotification();
        val creditRequestStatus = SQLUtils.getCreditRequestStatus();
        assertEquals("DECLINED", creditRequestStatus);
    }

    @Test
    void shouldPayByDebitCardWithInalidNumber() throws SQLException, IOException {
        val paymentPage = mainPage.payByDebitCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.invalidCardNumber();
    }

    @Test
    void shouldPayByCreditCardWithInalidNumber() throws SQLException, IOException {
        val paymentPage = mainPage.payByCreditCard();
        val invalidCardInformation = DataHelper.getInvalidCardInformation();
        paymentPage.enterCardInfo(invalidCardInformation);
        paymentPage.invalidCardNumber();
    }

    @Test
    void shouldPayByDebitCardWithExpiredYear() throws SQLException, IOException {
        val paymentPage = mainPage.payByDebitCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.expiredYear();
    }

    @Test
    void shouldPayByCreditCardWithExpiredYear() throws SQLException, IOException {
        val paymentPage = mainPage.payByCreditCard();
        val expiredYearCardInformation = DataHelper.getExpiredYearCardInformation();
        paymentPage.enterCardInfo(expiredYearCardInformation);
        paymentPage.expiredYear();
    }

    @Test
    void shouldPayByDebitCardWithInvalidExpirationDate() throws SQLException, IOException {
        val paymentPage = mainPage.payByDebitCard();
        val invalidExpirationDateCardInformation = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDateCardInformation);
        paymentPage.invalidExpirationDate();
    }

    @Test
    void shouldPayByCreditCardWithInvalidExpirationDate() throws SQLException, IOException {
        val paymentPage = mainPage.payByCreditCard();
        val invalidExpirationDateCardInformation = DataHelper.getInvalidExpirationDateCardInformation();
        paymentPage.enterCardInfo(invalidExpirationDateCardInformation);
        paymentPage.invalidExpirationDate();
    }

    @Test
    void shouldPayByDebitCardWithExpiredMonth() throws SQLException, IOException {
        val paymentPage = mainPage.payByDebitCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonthCardInformation);
        paymentPage.expiredMonth();
    }

    @Test
    void shouldPayByCreditCardWithExpiredMonth() throws SQLException, IOException {
        val paymentPage = mainPage.payByCreditCard();
        val expiredMonthCardInformation = DataHelper.getExpiredMonthCardInformation();
        paymentPage.enterCardInfo(expiredMonthCardInformation);
        paymentPage.expiredMonth();
    }

    @Test
    void shouldPayByDebitCardWithEmptyCardInformation() throws SQLException, IOException {
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
    void shouldPayByCreditCardWithEmptyCardInformation() throws SQLException, IOException {
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
    void shouldPayByDebitCardWithValidCardNumberAndInvalidOtherFields() throws SQLException, IOException {
        val paymentPage = mainPage.payByDebitCard();
        val validCardNumberWithInvalidOtherFields = DataHelper.getValidCardNumberWithInvalidOtherFields();
        paymentPage.enterCardInfo(validCardNumberWithInvalidOtherFields);
        paymentPage.invalidMonth();
        paymentPage.invalidYear();
        paymentPage.invalidOwner();
        paymentPage.invalidCvc();
    }

    @Test
    void shouldPayByCreditCardWithValidCardNumberAndInvalidOtherFields() throws SQLException, IOException {
        val paymentPage = mainPage.payByCreditCard();
        val validCardNumberWithInvalidOtherFields = DataHelper.getValidCardNumberWithInvalidOtherFields();
        paymentPage.enterCardInfo(validCardNumberWithInvalidOtherFields);
        paymentPage.invalidMonth();
        paymentPage.invalidYear();
        paymentPage.invalidOwner();
        paymentPage.invalidCvc();
    }

    @AfterAll
    static void cleanDb() throws SQLException, IOException {
        SQLUtils.cleanTables();
    }
}



