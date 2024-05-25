package com.develhope.spring.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {
        private Long id_user;

        private String email;

        private String restaurantName;

        private String restaurantPhoneNumber;

        private AddressEntity addressEntity;

        String description;

        boolean isDeliveryAvaible;
        boolean isTakeAwayAvaible;

        private List<ItemEntity> items = new ArrayList<>();

        private List<OperationHoursEntity> operatingHours;

    public RestaurantDto(Long id_user, String email, String restaurantName, String restaurantPhoneNumber, AddressEntity address, String description, boolean isDeliveryAvaible, boolean isTakeAwayAvaible, List<ItemEntity> items, List<OperationHoursEntity> operatingHours) {
        this.id_user = id_user;
        this.email = email;
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressEntity = address;
        this.description = description;
        this.isDeliveryAvaible = isDeliveryAvaible;
        this.isTakeAwayAvaible = isTakeAwayAvaible;
        this.items = items;
        this.operatingHours = operatingHours;
    }

    public Long getId_user() {
        return id_user;
    }

    public void setId_user(Long id_user) {
        this.id_user = id_user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantPhoneNumber() {
        return restaurantPhoneNumber;
    }

    public void setRestaurantPhoneNumber(String restaurantPhoneNumber) {
        this.restaurantPhoneNumber = restaurantPhoneNumber;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsDeliveryAvaible() {
        return isDeliveryAvaible;
    }

    public void setIsDeliveryAvaible(boolean deliveryAvaible) {
        isDeliveryAvaible = deliveryAvaible;
    }

    public boolean getIsTakeAwayAvaible() {
        return isTakeAwayAvaible;
    }

    public void setIsTakeAwayAvaible(boolean takeAwayAvaible) {
        isTakeAwayAvaible = takeAwayAvaible;
    }

    public List<ItemEntity> getItems() {
        return items;
    }

    public void setItems(List<ItemEntity> items) {
        this.items = items;
    }

    public List<OperationHoursEntity> getOperatingHours() {
        return operatingHours;
    }

    public void setOperatingHours(List<OperationHoursEntity> operatingHours) {
        this.operatingHours = operatingHours;
    }
}
