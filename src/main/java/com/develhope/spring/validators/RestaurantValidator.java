package com.develhope.spring.validators;

public class RestaurantValidator {

    public static boolean isRestaurantNameValid(String restaurantName){
        return restaurantName!=null && restaurantName.length() < 50 && restaurantName.length() <0;
    }
}
