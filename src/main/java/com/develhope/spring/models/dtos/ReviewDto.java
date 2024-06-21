package com.develhope.spring.models.dtos;

import com.develhope.spring.models.Rating;

import java.time.LocalDateTime;

public class ReviewDto {

    private String id;

    private String orderId;

    private LocalDateTime creationDate = LocalDateTime.now();

    private Rating rating;

    private String comment;

    private String restaurantId;

    private String customerId;

    public ReviewDto() {
    }

    public ReviewDto(String id,
                     String orderId,
                     LocalDateTime creationDate,
                     Rating rating,
                     String comment,
                     String restaurantId,
                     String customerId) {
        this.id = id;
        this.orderId = orderId;
        this.creationDate = creationDate;
        this.rating = rating;
        this.comment = comment;
        this.restaurantId = restaurantId;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}