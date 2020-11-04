package ru.netology.data;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateGenerator {
    static Random random = new Random();
    LocalDate today = LocalDate.now();
    DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
    DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");

    @Value
    public static class Year {
        private String year;
    }

    public Year getCurrentYear() {
        LocalDate currentYear = LocalDate.now();
        return new Year(yearFormatter.format(currentYear));
    }

    public Year getValidExpirationDate() {
        LocalDate newYear = today.plusYears(1);
        return new Year(yearFormatter.format(newYear));
    }

    public Year getExpiredYear() {
        LocalDate newYear = today.minusYears(1);
        return new Year(yearFormatter.format(newYear));
    }

    public Year getInvalidExpirationDate() {
        LocalDate newYear = today.plusYears(6);
        return new Year(yearFormatter.format(newYear));
    }

    public Year getInvalidYear() {
        int min = 30;
        int max = 99;
        int diff = max - min;
        int newYear = random.nextInt(diff + 1) + min;
        return new Year(Integer.toString(newYear));
    }

    @Value
    public static class Month {
        private String month;
    }

    public Month getValidMonth() {
        LocalDate newMonth = today.plusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    public Month getExpiredMonth() {
        LocalDate newMonth = today.minusMonths(1);
        return new Month(monthFormatter.format(newMonth));
    }

    public Month getInvalidMonth() {
        int min = 13;
        int max = 99;
        int diff = max - min;
        int newMonth = random.nextInt(diff + 1) + min;
        return new Month(Integer.toString(newMonth));
    }
}