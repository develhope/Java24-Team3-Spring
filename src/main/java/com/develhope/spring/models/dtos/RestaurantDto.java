package com.develhope.spring.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {
        private Long id_restaurant;

        private String email;

        private String restaurantName;

        private String restaurantPhoneNumber;

       private AddressDto addressDto ;

        String description;

        Boolean isDeliveryAvailable;
        Boolean isTakeAwayAvailable;

        private List<ProductDto> productDtos = new ArrayList<>();

        private List<OperatingHoursDto> operatingHoursDto;

    public RestaurantDto(Long id_user, String email, String restaurantName, String restaurantPhoneNumber, AddressDto addressDto, String description, Boolean isDeliveryAvaible, Boolean isTakeAwayAvaible, List<ProductDto> productDtos, List<OperatingHoursDto> operatingHoursDto) {
        this.id_restaurant = id_user;
        this.email = email;
        this.restaurantName = restaurantName;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressDto = addressDto;
        this.description = description;
        this.isDeliveryAvailable = isDeliveryAvaible;
        this.isTakeAwayAvailable = isTakeAwayAvaible;
        this.productDtos = productDtos;
        this.operatingHoursDto = operatingHoursDto;
    }

    public Long getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(Long id_restaurant) {
        this.id_restaurant = id_restaurant;
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

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public List<OperatingHoursDto> getOperatingHoursDto() {
        return operatingHoursDto;
    }

    public void setOperatingHours(List<OperatingHoursDto> operatingHoursDto) {
        this.operatingHoursDto = operatingHoursDto;
    }
}
