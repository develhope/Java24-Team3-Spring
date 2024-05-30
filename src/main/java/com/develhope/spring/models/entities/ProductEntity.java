package com.develhope.spring.models.entities;

import jakarta.persistence.*;

@Entity
@Table(name="product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_product;

    public ProductEntity() {
    }

    public ProductEntity(Long id_product) {
        this.id_product = id_product;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }
}
