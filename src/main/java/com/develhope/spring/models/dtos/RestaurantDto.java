package com.develhope.spring.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDto {
    private String id_restaurant;

    private OwnerDto ownerDto;

    private String restaurantName;

    private String restaurantEmail;

    private String restaurantPhoneNumber;

    private AddressDto addressDto;

    String description;

    Boolean isDeliveryAvailable;
    Boolean isTakeAwayAvailable;

    private List<OperatingHoursDto> operatingHoursDtos;

    private List<RestaurantTypeDto> restaurantTypeDtos = new ArrayList<>();

    private List<ProductDto> productDtos = new ArrayList<>();

    public RestaurantDto() {
    }

    public RestaurantDto(String id_restaurant, OwnerDto ownerDto, String restaurantName, String restaurantEmail, String restaurantPhoneNumber, AddressDto addressDto, String description, Boolean isDeliveryAvailable, Boolean isTakeAwayAvailable, List<OperatingHoursDto> operatingHoursDto, List<RestaurantTypeDto> restaurantTypeDtoList, List<ProductDto> productDtos) {
        this.id_restaurant = id_restaurant;
        this.ownerDto = ownerDto;
        this.restaurantName = restaurantName;
        this.restaurantEmail = restaurantEmail;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressDto = addressDto;
        this.description = description;
        this.isDeliveryAvailable = isDeliveryAvailable;
        this.isTakeAwayAvailable = isTakeAwayAvailable;
        this.operatingHoursDtos = operatingHoursDto;
        this.restaurantTypeDtos = restaurantTypeDtoList;
        this.productDtos = productDtos;
    }

    public OwnerDto getOwnerDto() {
        return ownerDto;
    }

    public void setOwnerDto(OwnerDto ownerDto) {
        this.ownerDto = ownerDto;
    }

    public List<RestaurantTypeDto> getRestaurantTypeDtos() {
        return restaurantTypeDtos;
    }

    public void setRestaurantTypeDtos(List<RestaurantTypeDto> restaurantTypeDtos) {
        this.restaurantTypeDtos = restaurantTypeDtos;
    }

    public String getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(String id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public String getRestaurantEmail() {
        return restaurantEmail;
    }

    public void setRestaurantEmail(String restaurantEmail) {
        this.restaurantEmail = restaurantEmail;
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

    public void setAddressDto(AddressDto addressDto) {
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

    public void setIsDeliveryAvailable(Boolean isDeliveryAvailable) {
        this.isDeliveryAvailable = isDeliveryAvailable;
    }

    public Boolean getIsTakeAwayAvailable() {
        return isTakeAwayAvailable;
    }

    public void setIsTakeAwayAvailable(Boolean isTakeAwayAvailable) {
        this.isTakeAwayAvailable = isTakeAwayAvailable;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }

    public List<OperatingHoursDto> getOperatingHoursDtos() {
        return operatingHoursDtos;
    }

    public void setOperatingHoursDtos(List<OperatingHoursDto> operatingHoursDtos) {
        this.operatingHoursDtos = operatingHoursDtos;
    }
}
