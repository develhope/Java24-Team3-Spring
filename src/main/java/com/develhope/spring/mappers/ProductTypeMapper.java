package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

    public ProductTypeEntity toEntity(ProductTypeDto productTypeDTO) {
        if (productTypeDTO == null) {
            return null;
        }

        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setId(productTypeDTO.getId());
        productTypeEntity.setProductType(productTypeDTO.getProductType());

        return productTypeEntity;
    }

    public ProductTypeDto toDto(ProductTypeEntity productTypeEntity) {
        if (productTypeEntity == null) {
            return null;
        }

        ProductTypeDto productTypeDto = new ProductTypeDto();
        productTypeDto.setId(productTypeEntity.getId());
        productTypeDto.setProductType(productTypeEntity.getProductType());

        return productTypeDto;
    }

}
