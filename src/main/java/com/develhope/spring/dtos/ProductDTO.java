package com.develhope.spring.dtos;

import java.math.BigInteger;

public class ProductDTO {

    private String name;

    private BigInteger price;

    private String ingredients;

    public ProductDTO() {
    }

    public ProductDTO(String name, BigInteger price, String ingredients) {
        this.name = name;
        this.price = price;
        this.ingredients = ingredients;
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
}
