package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "productType")
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "productType", unique = true)
    private String productType;

    public ProductTypeEntity() {
    }

    public ProductTypeEntity(String productType) {
        this.productType = productType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}
