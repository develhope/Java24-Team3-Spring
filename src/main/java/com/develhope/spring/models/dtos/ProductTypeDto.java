package com.develhope.spring.models.dtos;

public class ProductTypeDto {

    private String id;
    private String productType;

    public ProductTypeDto() {
    }

    public ProductTypeDto(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
}