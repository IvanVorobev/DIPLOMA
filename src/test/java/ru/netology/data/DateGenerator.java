package ru.netology.data;

import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class DateGenerator {
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
        Random random = new Random();
        int newYear = 30 + random.nextInt(99 - 30);
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
        Random random = new Random();
        int newMonth = 13 + random.nextInt(99 - 13);
        return new Month(Integer.toString(newMonth));
    }
}