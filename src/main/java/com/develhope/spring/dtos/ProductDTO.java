package com.develhope.spring.dtos;

import com.develhope.spring.entities.ProductTypeEntity;

import java.math.BigInteger;
import java.util.List;

public class ProductDTO {

    private String name;

    private BigInteger price;

    private String ingredients;

    private List<ProductTypeEntity> productTypes;

    public ProductDTO() {
    }

    public ProductDTO(String name, BigInteger price, String ingredients, List<ProductTypeEntity> productTypes) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
        this.productTypes = productTypes;
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
