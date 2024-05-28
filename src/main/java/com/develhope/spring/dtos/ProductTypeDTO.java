package com.develhope.spring.dtos;

public class ProductTypeDTO {

    private String productType;

    public ProductTypeDTO() {
    }

    public ProductTypeDTO(String productType) {
        this.productType = productType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

}
