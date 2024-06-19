package com.develhope.spring.models.dtos;

import java.math.BigDecimal;
import java.util.List;

public class RestaurantByLocationDto {
    String id_restaurant;

    String restaurantName;

    Byte[] restaurantImage;

    String rating;

    AddressDto addressDto;

    BigDecimal distance;

    BigDecimal deliveryTime;

    boolean isDeliveryAvaiable;

    boolean isTakeAwayAvaible;

    List<OperatingHoursDto> operatingHoursDtos;

    public RestaurantByLocationDto(String id_restaurant, String restaurantName, Byte[] restaurantImage, String rating, AddressDto addressDto, BigDecimal distance, BigDecimal deliveryTime, boolean isDeliveryAvaiable, boolean isTakeAwayAvaible, List<OperatingHoursDto> operatingHoursDtos) {
        this.id_restaurant = id_restaurant;
        this.restaurantName = restaurantName;
        this.restaurantImage = restaurantImage;
        this.rating = rating;
        this.addressDto = addressDto;
        this.distance = distance;
        this.deliveryTime = deliveryTime;
        this.isDeliveryAvaiable = isDeliveryAvaiable;
        this.isTakeAwayAvaible = isTakeAwayAvaible;
        this.operatingHoursDtos = operatingHoursDtos;
    }

    public String getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(String id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public Byte[] getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(Byte[] restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressDto(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public BigDecimal getDistance() {
        return distance;
    }

    public RestaurantByLocationDto setDistance(BigDecimal distance) {
        this.distance = distance;
        return this;
    }

    public BigDecimal getDeliveryTime() {
        return deliveryTime;
    }

    public RestaurantByLocationDto setDeliveryTime(BigDecimal deliveryTime) {
        this.deliveryTime = deliveryTime;
        return this;
    }

    public boolean getIsDeliveryAvaiable() {
        return isDeliveryAvaiable;
    }

    public void setIsDeliveryAvaiable(boolean deliveryAvaiable) {
        isDeliveryAvaiable = deliveryAvaiable;
    }

    public boolean getIsTakeAwayAvaible() {
        return isTakeAwayAvaible;
    }

    public void setIsTakeAwayAvaible(boolean takeAwayAvaible) {
        isTakeAwayAvaible = takeAwayAvaible;
    }
}
