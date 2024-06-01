package com.develhope.spring.utils;

import org.springframework.stereotype.Component;

@Component
public class DistanceCalculator {

    // Earth radius in kilometers
    private static final double EARTH_RADIUS = 6371.0;

    /**
     * @author Chiara De Guglielmo
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
     */
    // haversine formula: https://en.wikipedia.org/wiki/Haversine_formula#:~:text=The%20term%20haversine%20was%20coined,sin2(%CE%B82).
    // trigonometc formula: https://en.wikipedia.org/wiki/Inverse_trigonometric_functions
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

}

