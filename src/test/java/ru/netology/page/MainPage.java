package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement paymentByDebitCardButton = $(byText("Купить"));
    private SelenideElement paymentByCreditCardButton = $(byText("Купить в кредит"));
    private SelenideElement paymentHeading = $("#root>div>h3.heading.heading_size_m.heading_theme_alfa-on-white");

    public PaymentPage payByDebitCard() {
        paymentByDebitCardButton.click();
        paymentHeading.shouldHave(Condition.text("Оплата по карте"));
        return new PaymentPage();
    }

    public PaymentPage payByCreditCard() {
        paymentByCreditCardButton.click();
        paymentHeading.shouldHave(Condition.text("Кредит по данным карты"));
        return new PaymentPage();
    }
}
