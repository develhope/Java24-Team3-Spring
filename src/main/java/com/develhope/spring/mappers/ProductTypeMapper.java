package com.develhope.spring.mappers;

import com.develhope.spring.dtos.ProductTypeDTO;
import com.develhope.spring.entities.ProductTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

    public ProductTypeEntity asEntity(ProductTypeDTO productTypeDTO) {
        if (productTypeDTO == null) {
            return null;
        }

        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setProductType(productTypeDTO.getProductType());

        return productTypeEntity;
    }

    public ProductTypeDTO asDto(ProductTypeEntity productTypeEntity) {
        if (productTypeEntity == null) {
            return null;
        }

        ProductTypeDTO productTypeDTO = new ProductTypeDTO();
        productTypeDTO.setProductType(productTypeEntity.getProductType());

        return productTypeDTO;
    }

}
