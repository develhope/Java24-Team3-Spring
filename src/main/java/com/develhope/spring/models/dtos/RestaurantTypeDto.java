package com.develhope.spring.models.dtos;

public class RestaurantTypeDto {
    private String restaurantType;

    public RestaurantTypeDto() {
    }

    public RestaurantTypeDto(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    @Override
    public String toString() {
        return restaurantType ;
    }
}
