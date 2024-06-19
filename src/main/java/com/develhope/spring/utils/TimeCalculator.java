package com.develhope.spring.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class TimeCalculator {

    /**
     * @param distance The diagonal of the square (in any unit).
     * @param velocity The velocity of travel (in the same unit per time unit as distance).
     * @return The time to travel the semiperimeter of the square (in the same time unit as velocity).
     * @effect Calculate the time to travel the semiperimeter of a square, given that the diagonal of the square is distance
     * and the travel velocity is velocity.
     */
    public static BigDecimal calculateTime(BigDecimal distance, BigDecimal velocity) {
        // Precision for the calculation
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

        // Calculate the side of the square: side = distance / sqrt(2)
        BigDecimal sqrt2 = new BigDecimal(Math.sqrt(2), mc);
        BigDecimal side = distance.divide(sqrt2, mc);

        // Calculate the semiperimeter: semiperimeter = 2 * side
        BigDecimal semiperimeter = side.multiply(new BigDecimal(2), mc);

        // Calculate the time: time = semiperimeter / velocity
        BigDecimal time = semiperimeter.divide(velocity, mc);

        return time;
    }
}