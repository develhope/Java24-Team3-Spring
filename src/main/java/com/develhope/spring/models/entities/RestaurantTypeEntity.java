package com.develhope.spring.models.entities;


import jakarta.persistence.*;

@Entity
@Table(name = "restaurantType")
public class RestaurantTypeEntity {
    @Id
    private String restaurantType;

    public RestaurantTypeEntity() {
    }

    public RestaurantTypeEntity(String restaurantType) {
        this.restaurantType = restaurantType;
    }

    public String getRestaurantType() {
        return restaurantType;
    }

    public void setRestaurantType(String restaurantType) {
        this.restaurantType = restaurantType;
    }
}
