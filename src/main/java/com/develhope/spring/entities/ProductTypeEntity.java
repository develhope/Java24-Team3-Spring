package com.develhope.spring.entities;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table
public class ProductTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private List<String> productType;

    public ProductTypeEntity() {
    }

    public ProductTypeEntity(UUID id, List<String> productType) {
        this.id = id;
        this.productType = productType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public List<String> getProductType() {
        return productType;
    }

    public void setProductType(List<String> productType) {
        this.productType = productType;
    }
}
