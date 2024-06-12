package com.develhope.spring.models.dtos;

public class ProductTypeDto {

    private Long id;
    private String productType;

    public ProductTypeDto() {
    }

    public ProductTypeDto(String productType) {
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