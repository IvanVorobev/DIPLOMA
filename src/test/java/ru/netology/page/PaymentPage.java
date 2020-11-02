package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class PaymentPage {
    private SelenideElement cardNumberField = $(".input__box>input.input__control[placeholder=\"0000 0000 0000 0000\"]");
    private SelenideElement monthField = $(".input__box>input.input__control[placeholder=\"08\"]");
    private SelenideElement yearField = $(".input__box>input.input__control[placeholder=\"22\"]");
    private SelenideElement ownerField = $("fieldset > div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__box > input");
    private SelenideElement cvcField = $(".input__box>input.input__control[placeholder=\"999\"]");
    private SelenideElement continueButton = $(byText("Продолжить"));

    private SelenideElement sucsessNotification = $(withText("Успешно"));
    private SelenideElement errorNotification = $(withText("Ошибка"));
    private SelenideElement cardNumberFieldWarning = $("fieldset > div:nth-child(1) > span > span > span.input__sub");
    private SelenideElement monthFieldWarning = $("div:nth-child(2) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement yearFieldWarning = $("div:nth-child(2) > span > span:nth-child(2) > span > span > span.input__sub");
    private SelenideElement ownerFieldWarning = $("div:nth-child(3) > span > span:nth-child(1) > span > span > span.input__sub");
    private SelenideElement cvcFieldWarning = $("div:nth-child(3) > span > span:nth-child(2) > span > span > span.input__sub");

    public void enterCardInfo(DataHelper.CardInformation cardInformation) {
        cardNumberField.setValue(cardInformation.getCardNumber());
        monthField.setValue(cardInformation.getMonth());
        yearField.setValue(cardInformation.getYear());
        ownerField.setValue(cardInformation.getOwner());
        cvcField.setValue(cardInformation.getCvc());
        continueButton.click();
    }

    public void sucsessNotification(){
        sucsessNotification.waitUntil(Condition.visible, 15000);
    }

    public void errorNotification(){
        errorNotification.waitUntil(Condition.visible, 15000);
    }

    public void invalidCardNumber(){
        cardNumberFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidMonth(){
        monthFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidYear(){
        yearFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidOwner(){
        ownerFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void invalidCvc(){
        cvcFieldWarning.shouldHave(text("Неверный формат"));
    }

    public void emptyCardNumber(){
        cardNumberFieldWarning.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void emptyMonth(){
        monthFieldWarning.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void emptyYear(){
        yearFieldWarning.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void emptyOwner(){
        ownerFieldWarning.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void emptyCvc(){
        cvcFieldWarning.shouldHave(text("Поле обязательно для заполнения"));
    }

    public void expiredYear(){
        yearFieldWarning.shouldHave(text("Истёк срок действия карты"));
    }

    public void expiredMonth(){
        monthFieldWarning.shouldHave(text("Неверно указан срок действия карты"));
    }

    public void invalidExpirationDate(){
        yearFieldWarning.shouldHave(text("Неверно указан срок действия карты"));
    }
}
