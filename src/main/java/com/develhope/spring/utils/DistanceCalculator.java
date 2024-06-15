package com.develhope.spring.utils;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Component
public class DistanceCalculator {

    // Earth radius in kilometers
    private static final BigDecimal EARTH_RADIUS = new BigDecimal("6360.0"); // Earth radius in kilometers
    // more precision radious by latitude: https://rechneronline.de/earth-radius/

    /**
     * @effect Calculate distance between two points in latitude and longitude taking
     * into account height difference. If you are not interested in height
     * difference pass 0.0. Uses Haversine method as its base.
     * @param lat1 Latitude of point 1 (in degrees).
     * @param lat2 Latitude of point 2 (in degrees).
     * @param lon1 Longitude of point 1 (in degrees).
     * @param lon2 Longitude of point 2 (in degrees).
     * @param el1 Elevation of point 1 (optional, in meters).
     * @param el2 Elevation of point 2 (optional, in meters).
     * @return The distance between the two points (in meters).
     * @see <a href="https://en.wikipedia.org/wiki/Haversine_formula#:~:text=The%20term%20haversine%20was%20coined,sin2(%CE%B82)">Haversine formula</a>
     * @see <a href="https://en.wikipedia.org/wiki/Inverse_trigonometric_functions">Trigonometric formula</a>
     */
    public static BigDecimal calculateDistance(BigDecimal lat1, BigDecimal lat2, BigDecimal lon1, BigDecimal lon2, BigDecimal el1, BigDecimal el2) {
        MathContext mc = new MathContext(15, RoundingMode.HALF_UP);

        BigDecimal latDistance = toRadians(lat2.subtract(lat1, mc), mc);
        BigDecimal lonDistance = toRadians(lon2.subtract(lon1, mc), mc);

        BigDecimal a = sin(latDistance.divide(new BigDecimal(2), mc)).pow(2, mc)
                .add(
                        cos(toRadians(lat1, mc)).multiply(cos(toRadians(lat2, mc)), mc)
                                .multiply(sin(lonDistance.divide(new BigDecimal(2), mc)).pow(2, mc), mc), mc);

        BigDecimal c = new BigDecimal(2).multiply(atan2(sqrt(a, mc), sqrt(BigDecimal.ONE.subtract(a, mc), mc), mc), mc);

        // Calculate distance in meters
        BigDecimal distance = EARTH_RADIUS.multiply(c, mc).multiply(new BigDecimal(1000), mc);

        // Consider the height difference
        BigDecimal heightDifference = el1.subtract(el2, mc);
        distance = distance.pow(2, mc).add(heightDifference.pow(2, mc), mc);

        return sqrt(distance, mc);
    }

    private static BigDecimal toRadians(BigDecimal value, MathContext mc) {
        BigDecimal pi = new BigDecimal(Math.PI, mc);
        return value.multiply(pi, mc).divide(new BigDecimal(180), mc);
    }

    private static BigDecimal sin(BigDecimal value) {
        return new BigDecimal(Math.sin(value.doubleValue()));
    }

    private static BigDecimal cos(BigDecimal value) {
        return new BigDecimal(Math.cos(value.doubleValue()));
    }

    private static BigDecimal atan2(BigDecimal y, BigDecimal x, MathContext mc) {
        return new BigDecimal(Math.atan2(y.doubleValue(), x.doubleValue()), mc);
    }

    private static BigDecimal sqrt(BigDecimal value, MathContext mc) {
        return value.sqrt(mc);
    }


/*    VERSIONE CON I DOUBLE:

    private static final BigDecimal EARTH_RADIUS = 6371.0;

    public static double calculateDistance(double lat1, double lat2, double lon1, double lon2, double el1, double el2) {
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Calculate distance in meters
        double distance = EARTH_RADIUS * c * 1000;

        // Consider the height difference
        double heightDifference = el1 - el2;
        distance = Math.pow(distance, 2) + Math.pow(heightDifference, 2);

        return Math.sqrt(distance);
    }

 */

}

