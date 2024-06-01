package com.develhope.spring.models.dtos;

import com.develhope.spring.models.entities.ProductTypeEntity;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private String id;

    private String name;

    private BigDecimal price;

    private String ingredients;

    private List<ProductTypeEntity> productTypes;

    public ProductDto() {
    }

    public ProductDto(String id, String name, BigDecimal price, String ingredients, List<ProductTypeEntity> productTypes) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.productTypes = productTypes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
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