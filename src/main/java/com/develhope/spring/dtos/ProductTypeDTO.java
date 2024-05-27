package com.develhope.spring.dtos;

import java.util.List;

public class ProductTypeDTO {

    private List<String> productType;

    public ProductTypeDTO() {
    }

    public ProductTypeDTO(List<String> productType) {
        this.productType = productType;
    }

    public List<String> getProductType() {
        return productType;
    }

    public void setProductType(List<String> productType) {
        this.productType = productType;
    }

}
