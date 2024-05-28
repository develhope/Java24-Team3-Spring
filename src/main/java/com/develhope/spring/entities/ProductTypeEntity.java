package com.develhope.spring.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "productTypes")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, name = "productType")
    private String productType;

    public ProductTypeEntity() {
    }

    public ProductTypeEntity(UUID id, String productType) {
        this.id = id;
        this.productType = productType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
