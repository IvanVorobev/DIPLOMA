package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {
    static DateGenerator dateGenerator = new DateGenerator();
    static Random random = new Random();

    public static String getApprovedCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String getDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String getInvalidCardNumber() {
        return "1111 1111 1111 111";
    }

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return faker.name().fullName();
    }

    public static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().fullName();
    }

    public static String getValidCvc() {
        int min = 100;
        int max = 999;
        int diff = max - min;
        int cvc = random.nextInt(diff + 1) + min;
        return Integer.toString(cvc);
    }

    public static String getInvalidCvc() {
        int min = 10;
        int max = 99;
        int diff = max - min;
        int cvc = random.nextInt(diff + 1) + min;
        return Integer.toString(cvc);
    }

    @Value
    public static class CardInformation {
        private String cardNumber;
        private String year;
        private String month;
        private String owner;
        private String cvc;
    }

    public static CardInformation getApprovedCardInformation() {
        return new CardInformation(getApprovedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInformation getDeclinedCardInformation() {
        return new CardInformation(getDeclinedCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInformation getInvalidCardInformation() {
        return new CardInformation(getInvalidCardNumber(), dateGenerator.getValidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInformation getExpiredMonthCardInformation() {
        return new CardInformation(getApprovedCardNumber(), dateGenerator.getCurrentYear().getYear(), dateGenerator.getExpiredMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInformation getExpiredYearCardInformation() {
        return new CardInformation(getApprovedCardNumber(), dateGenerator.getExpiredYear().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInformation getInvalidExpirationDateCardInformation() {
        return new CardInformation(getApprovedCardNumber(), dateGenerator.getInvalidExpirationDate().getYear(), dateGenerator.getValidMonth().getMonth(), getValidOwner(), getValidCvc());
    }

    public static CardInformation getEmptyCardInformation() {
        return new CardInformation(" ", " ", " ", " ", " ");
    }

    public static CardInformation getValidCardNumberWithInvalidOtherFields() {
        return new CardInformation(getApprovedCardNumber(), dateGenerator.getInvalidYear().getYear(), dateGenerator.getInvalidMonth().getMonth(), getInvalidOwner(), getInvalidCvc());
    }
}
