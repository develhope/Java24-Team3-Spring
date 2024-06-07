package com.develhope.spring.models.dtos;

import java.util.List;

public class CartDto {

    private String id;

    private List<CartProductDto> cartProducts;

    public CartDto() {
    }

    public CartDto(List<CartProductDto> cartProducts) {
        this.cartProducts = cartProducts;
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
}
