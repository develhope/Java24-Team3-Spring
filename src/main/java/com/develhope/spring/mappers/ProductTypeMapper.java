package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeMapper {

    public ProductTypeEntity asEntity(ProductTypeDto productTypeDto) {
        if (productTypeDto == null) {
            return null;
        }

        ProductTypeEntity productTypeEntity = new ProductTypeEntity(
                productTypeDto.getId(),
                productTypeDto.getProductType()
        );

        return productTypeEntity;
    }

    public ProductTypeDto asDto(ProductTypeEntity productTypeEntity) {
        if (productTypeEntity == null) {
            return null;
        }

        ProductTypeDto productTypeDto = new ProductTypeDto(
                productTypeEntity.getId(),
                productTypeEntity.getProductType()
        );

        return productTypeDto;
    }

}