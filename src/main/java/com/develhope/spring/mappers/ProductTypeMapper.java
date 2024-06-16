package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductTypeMapper {

    public ProductTypeEntity toEntity(ProductTypeDto productTypeDTO) {
        if (productTypeDTO == null) {
            return null;
        }

        ProductTypeEntity productTypeEntity = new ProductTypeEntity();
        productTypeEntity.setProductType(productTypeDTO.getProductType());

        return productTypeEntity;
    }

    public ProductTypeDto toDto(ProductTypeEntity productTypeEntity) {
        if (productTypeEntity == null) {
            return null;
        }

        ProductTypeDto productTypeDto = new ProductTypeDto();
        productTypeDto.setProductType(productTypeEntity.getProductType());

        return productTypeDto;
    }

    public List<ProductTypeEntity> toEntityList(List<ProductTypeDto> productTypeDTOs){
        if (productTypeDTOs == null) {
            return null;
        }

        List<ProductTypeEntity> productTypeEntities = new ArrayList<>(productTypeDTOs.size());
        for (ProductTypeDto productTypeDto : productTypeDTOs) {
            productTypeEntities.add(toEntity(productTypeDto));
        }

        return productTypeEntities;
    }

    public List<ProductTypeDto> toDtoList(List<ProductTypeEntity> productTypeEntities){
        if (productTypeEntities == null) {
            return null;
        }

        List<ProductTypeDto> productTypeDTOs = new ArrayList<>(productTypeEntities.size());
        for (ProductTypeEntity productTypeEntity : productTypeEntities) {
            productTypeDTOs.add(toDto(productTypeEntity));
        }

        return productTypeDTOs;
    }

}
