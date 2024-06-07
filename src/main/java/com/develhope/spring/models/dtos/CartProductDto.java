package com.develhope.spring.models.dtos;

public class CartProductDto {

    private String id;

    private int quantity;

    private ProductDto product;

    private CartDto cart;

    public CartProductDto() {
    }

    public CartProductDto(int quantity, ProductDto product, CartDto cart) {
        this.quantity = quantity;
        this.product = product;
        this.cart = cart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }
}
