package com.develhope.spring.models.dtos;

import com.develhope.spring.utils.OrderStatus;
import com.develhope.spring.utils.OrderType;

import java.time.LocalDateTime;

public class OrderDto {

    private String id;

    private OrderType type;

    private OrderStatus status;

    private LocalDateTime creationDate = LocalDateTime.now();

    private String customerId;

    private String cartId;

    private String paymentId;

    private String restaurantId;

    // CONSTRUCTORS

    public OrderDto() {
    }

    public OrderDto(String id, OrderType type, OrderStatus status, LocalDateTime creationDate, String customerId, String cartId, String paymentId, String restaurantId) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.creationDate = creationDate;
        this.customerId = customerId;
        this.cartId = cartId;
        this.paymentId = paymentId;
        this.restaurantId = restaurantId;
    }

    // GETTERS AND SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }
}