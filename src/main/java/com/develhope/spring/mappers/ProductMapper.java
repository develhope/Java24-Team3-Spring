package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    @Autowired
    ProductTypeMapper productTypeMapper;

    public ProductEntity toEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        List<ProductTypeEntity> productTypeEntities = new ArrayList<>();
        if (productDto.getProductTypes()==null){
            productTypeEntities=null;
        } else {
            for (ProductTypeDto productTypeDto : productDto.getProductTypes()) {
                productTypeEntities.add(
                        productTypeMapper.toEntity(productTypeDto)
                );
            }
        }

        ProductEntity productEntity = new ProductEntity(
                productDto.getId(),
                productDto.getName(),
                productDto.getPrice(),
                productDto.getIngredients(),
                productTypeEntities
        );

        return productEntity;
    }

    public ProductDto toDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        List<ProductTypeDto> productTypeDtos = new ArrayList<>();
        if (productEntity.getProductTypes()==null){
            productTypeDtos=null;
        } else {
            for (ProductTypeEntity productTypeEntity : productEntity.getProductTypes()) {
                productTypeDtos.add(
                        productTypeMapper.toDto(productTypeEntity)
                );
            }
        }

        ProductDto productDto = new ProductDto(
                productEntity.getId(),
                productEntity.getName(),
                productEntity.getPrice(),
                productEntity.getIngredients(),
                productTypeDtos
        );

        return productDto;
    }

}