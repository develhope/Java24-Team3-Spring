package com.develhope.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "customer")
public class CustomerEntity extends UserEntity {

    @OneToOne(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference(value = "cart_customer")
    private CartEntity cart;

    public CustomerEntity() {
    }

    public CustomerEntity(String email,
                          String password) {
        super(email, password);
    }

    public CustomerEntity(String email,
                          String password,
                          Boolean isDeleted,
                          Boolean isVerified,
                          UserDetailsEntity userDetails,
                          CartEntity cart) {
        super(email, password, isDeleted, isVerified, userDetails);
        this.cart = cart;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

}