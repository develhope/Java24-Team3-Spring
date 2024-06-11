package com.develhope.spring.models.dtos;

public class CartProductDto {

    private String id;

    private int quantity;

    private String productId;

    private String cartId;

    public CartProductDto() {
    }

    public CartProductDto(int quantity, String productId, String cartId) {
        this.quantity = quantity;
        this.productId = productId;
        this.cartId = cartId;
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

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
}
