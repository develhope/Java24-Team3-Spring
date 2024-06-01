package com.develhope.spring.models.dtos;



import java.math.BigInteger;
import java.util.List;

public class ProductDto {
    private String id;

    private String name;

    private BigInteger price;

    private String ingredients;

    private List<ProductTypeDto> productTypes;

    public ProductDto() {
    }

    public ProductDto(String id, String name, BigInteger price, String ingredients, List<ProductTypeDto> productTypes) {
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

    public List<ProductTypeDto> getProductTypes() {
        return productTypes;
    }

    public void setProductTypes(List<ProductTypeDto> productTypes) {
        this.productTypes = productTypes;
    }
}