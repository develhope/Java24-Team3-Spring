package com.develhope.spring.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "cart")
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JsonManagedReference(value = "a")
    private List<CartProductEntity> cartProducts;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "customer_id")
    private CustomerEntity customer;

    public CartEntity() {
    }

    public CartEntity(List<CartProductEntity> cartProducts, CustomerEntity customerEntity) {
        this.cartProducts = cartProducts;
        this.customer = customerEntity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<CartProductEntity> getCartProducts() {
        return cartProducts;
    }

    public void setCartProducts(List<CartProductEntity> cartProducts) {
        this.cartProducts = cartProducts;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
}
