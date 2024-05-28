package com.develhope.spring.entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigInteger price;

    private String ingredients;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "productType_id"))
    @Column(name = "productTypes")
    private List<ProductTypeEntity> productTypes;

    public ProductEntity() {
    }

    public ProductEntity(UUID id, String name, BigInteger price, String ingredients, List<ProductTypeEntity> productTypes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.productTypes = productTypes;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public List<ProductTypeEntity> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductTypeEntity> productTypes) {
        this.productTypes = productTypes;
    }
}
