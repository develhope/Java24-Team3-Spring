package com.develhope.spring.utils;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class DistanceCalculator_Euclide {

    /**
     * Calculates the distance between two points in a 3D Cartesian space.
     * @param x1 The latitude of the first point.
     * @param y1 The longitude of the first point.
     * @param z1 The height of the first point.
     * @param x2 The latitude of the second point.
     * @param y2 The longitude of the second point.
     * @param z2 The height of the second point.
     * @return The distance between the two points.
     */
    public static BigDecimal calculateDistance(BigDecimal x1, BigDecimal x2, BigDecimal y1, BigDecimal y2, BigDecimal z1, BigDecimal z2) {
        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

        // Calculate the differences
        BigDecimal latDifference = x2.subtract(x1, mc);
        BigDecimal lonDifference = y2.subtract(y1, mc);
        BigDecimal heightDifference = z2.subtract(z1, mc);

        // Calculate the squares of the differences
        BigDecimal latSquare = latDifference.pow(2, mc);
        BigDecimal lonSquare = lonDifference.pow(2, mc);
        BigDecimal heightSquare = heightDifference.pow(2, mc);

        // Sum the squares
        BigDecimal sumOfSquares = latSquare.add(lonSquare, mc).add(heightSquare, mc);

        // Return the square root of the sum of squares
        return sumOfSquares.sqrt(mc);
    }

    public static void main(String[] args) {
        BigDecimal lat1 = new BigDecimal("40.748817");
        BigDecimal lon1 = new BigDecimal("-73.985428");
        BigDecimal height1 = new BigDecimal("0");
        BigDecimal lat2 = new BigDecimal("34.052235");
        BigDecimal lon2 = new BigDecimal("-118.243683");
        BigDecimal height2 = new BigDecimal("0");

        BigDecimal distance = calculateDistance(lat1, lon1, height1, lat2, lon2, height2);
        System.out.println("Distance: " + distance + " units");
    }
}
