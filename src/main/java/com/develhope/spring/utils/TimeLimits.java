package com.develhope.spring.utils;

import java.time.LocalTime;

public class TimeLimits {

    private static final LocalTime startOfTheWorkDay = LocalTime.parse("00:00");
    private static final LocalTime endOfTheWorkDay = LocalTime.parse("23:59:59");
    private static final LocalTime startingHourLimit = LocalTime.parse("23:00");
    private static final LocalTime endingHourLimit = LocalTime.parse("01:00");

    // GETTERS

    public static LocalTime getStartOfTheWorkDay() {
        return startOfTheWorkDay;
    }

    public static LocalTime getEndOfTheWorkDay() {
        return endOfTheWorkDay;
    }

    public static LocalTime getStartingHourLimit() {
        return startingHourLimit;
    }

    public static LocalTime getEndingHourLimit() {
        return endingHourLimit;
    }

    // CHECKERS

    public static boolean isWithinTheLimits(LocalTime startingHour, LocalTime endingHour) {
        return (startingHour.isBefore(startingHourLimit) || endingHour.isAfter(endingHourLimit));
    }

}