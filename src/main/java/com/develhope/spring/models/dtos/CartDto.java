package com.develhope.spring.models.dtos;

import java.util.List;

public class CartDto {

    private String id;

    private List<CartProductDto> cartProducts;

    private String customerId;

    public CartDto() {
    }

    public CartDto(List<CartProductDto> cartProducts, String customerId) {
        this.cartProducts = cartProducts;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartProductDto> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductDto> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
}
