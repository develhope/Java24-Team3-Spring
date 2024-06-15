package com.develhope.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "cart_product")
public class CartProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    @JsonBackReference(value = "cart_cartProducts")
    private CartEntity cart;

    public CartProductEntity() {
    }

    public CartProductEntity(int quantity, ProductEntity product, CartEntity cart) {
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

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }
}
