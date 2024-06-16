package com.develhope.spring.models.dtos;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.math.BigDecimal;
import java.util.List;

public class ProductDto {

    private String id;

    private String name;

    private BigDecimal price;

    private String ingredients;

    private List<ProductTypeDto> productTypes;


    public ProductDto() {
    }

    public ProductDto(String name, BigDecimal price, String ingredients, List<ProductTypeDto> productTypes) {
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

    public List<ProductTypeDto> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductTypeDto> productTypes) {
        this.productTypes = productTypes;
    }
}
