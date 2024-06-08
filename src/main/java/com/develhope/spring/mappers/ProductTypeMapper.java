package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductTypeEntity;
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

    public List<ProductTypeDto> toDto(List<ProductTypeEntity> productTypeEntities) {
        if (productTypeEntities == null) return null;


        List<ProductTypeDto> productTypeDtos = new ArrayList<>();
        for (ProductTypeEntity p : productTypeEntities) {
            productTypeDtos.add(
                    this.toDto(p)
            );
        }
        return productTypeDtos;
    }

    public List<ProductTypeEntity> toEntity(List<ProductTypeDto> productTypeDtos) {
        if (productTypeDtos == null) return null;

        List<ProductTypeEntity> productTypeEntities = new ArrayList<>();
        for (ProductTypeDto p : productTypeDtos) {
            productTypeEntities.add(
                    this.toEntity(p)
            );
        }
        return productTypeEntities;
    }

}
