package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "productType")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false, name = "productType", unique = true)
    private String productType;

    public ProductTypeEntity() {
    }

    public ProductTypeEntity(String productType) {
        this.productType = productType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
