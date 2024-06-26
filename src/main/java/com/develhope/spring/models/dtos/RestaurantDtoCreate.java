package com.develhope.spring.models.dtos;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDtoCreate {

    private String id_owner;

    private String restaurantName;

    private String restaurantEmail;

    private String restaurantPhoneNumber;

    private AddressDto addressDto;

    String description;

    boolean isDeliveryAvailable;

    boolean isTakeAwayAvailable;

    private List<OperatingHoursDto> operatingHoursDtos;

    private List<RestaurantTypeDto> restaurantTypeDtos = new ArrayList<>();

    private List<ProductDto> productDtos = new ArrayList<>();

    private List<OrderDto> orders;

    private List<ReviewDto> reviews;

    public RestaurantDtoCreate() {
    }

    public RestaurantDtoCreate(String id_owner,
                               String restaurantName,
                               String restaurantEmail,
                               String restaurantPhoneNumber,
                               AddressDto addressDto,
                               String description,
                               boolean isDeliveryAvailable,
                               boolean isTakeAwayAvailable,
                               List<OperatingHoursDto> operatingHoursDtos,
                               List<RestaurantTypeDto> restaurantTypeDtos,
                               List<ProductDto> productDtos,
                               List<OrderDto> orders,
                               List<ReviewDto> reviews) {
        this.id_owner = id_owner;
        this.restaurantName = restaurantName;
        this.restaurantEmail = restaurantEmail;
        this.restaurantPhoneNumber = restaurantPhoneNumber;
        this.addressDto = addressDto;
        this.description = description;
        this.isDeliveryAvailable = isDeliveryAvailable;
        this.isTakeAwayAvailable = isTakeAwayAvailable;
        this.operatingHoursDtos = operatingHoursDtos;
        this.restaurantTypeDtos = restaurantTypeDtos;
        this.productDtos = productDtos;
        this.orders = orders;
        this.reviews = reviews;
    }

    public String getId_owner() {
        return id_owner;
    }

    public void setId_owner(String id_owner) {
        this.id_owner = id_owner;
    }

    public List<RestaurantTypeDto> getRestaurantTypeDtos() {
        return restaurantTypeDtos;
    }

    public void setRestaurantTypeDtos(List<RestaurantTypeDto> restaurantTypeDtos) {
        this.restaurantTypeDtos = restaurantTypeDtos;
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

    public List<OrderDto> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDto> orders) {
        this.orders = orders;
    }

    public List<ReviewDto> getReviews() {
        return reviews;
    }

    public void setReviews(List<ReviewDto> reviews) {
        this.reviews = reviews;
    }

}