package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {
    static DateGenerator dateGenerator = new DateGenerator();
    static Random random = new Random();

    public static String getApprovedCardNumber() {
        return new String("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return new String("4444 4444 4444 4442");
    }

    public static String getInvalidCardNumber() {
        return new String("1111 1111 1111 111");
    }

    public static String getValidOwner() {
        Faker faker = new Faker(new Locale("en"));
        return new String(faker.name().fullName());
    }

    public static String getInvalidOwner() {
        Faker faker = new Faker(new Locale("ru"));
        return new String(faker.name().fullName());
    }

    public static String getValidCvc() {
        int cvc = 100 + random.nextInt(999 - 100);
        return new String(Integer.toString(cvc));
    }

    public static String getInvalidCvc() {
        int cvc = 10 + random.nextInt(99 - 10);
        return new String(Integer.toString(cvc));
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
