package com.develhope.spring.models.dtos;

import com.develhope.spring.models.entities.ProductEntity;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {
        private Long id_user;

        private String email;

        private String restaurantName;

        private String restaurantPhoneNumber;

       private AddressDto addressDto ;

        String description;

        Boolean isDeliveryAvailable;
        Boolean isTakeAwayAvailable;

        private List<ProductEntity> productEntities = new ArrayList<>();

        private List<OperatingHoursDto> operatingHoursDto;

    public RestaurantDto(Long id_user, String email, String restaurantName, String restaurantPhoneNumber, AddressDto addressDto, String description, Boolean isDeliveryAvaible, Boolean isTakeAwayAvaible, List<ProductEntity> productEntities, List<OperatingHoursDto> operatingHoursDto) {
        this.id_user = id_user;
        this.email = email;
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressDto = addressDto;
        this.description = description;
        this.isDeliveryAvailable = isDeliveryAvaible;
        this.isTakeAwayAvailable = isTakeAwayAvaible;
        this.productEntities = productEntities;
        this.operatingHoursDto = operatingHoursDto;
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

    public AddressDto getAddressDto() {
        return addressDto;
    }

    public void setAddressEntity(AddressDto addressDto) {
        this.addressDto = addressDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getIsDeliveryAvailable() {
        return isDeliveryAvailable;
    }

    public void setIsDeliveryAvailable(boolean deliveryAvailable) {
        isDeliveryAvailable = deliveryAvailable;
    }

    public Boolean getIsTakeAwayAvailable() {
        return isTakeAwayAvailable;
    }

    public void setIsTakeAwayAvailable(boolean takeAwayAvailable) {
        isTakeAwayAvailable = takeAwayAvailable;
    }

    public List<ProductEntity> getProductEntities() {
        return productEntities;
    }

    public void setProductEntities(List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
    }

    public List<OperatingHoursDto> getOperatingHoursDto() {
        return operatingHoursDto;
    }

    public void setOperatingHours(List<OperatingHoursDto> operatingHoursDto) {
        this.operatingHoursDto = operatingHoursDto;
    }
}
