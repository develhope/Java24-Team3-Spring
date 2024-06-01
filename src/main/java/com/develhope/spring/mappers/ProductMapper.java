package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.OperatingHoursDto;
import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.OperatingHoursEntity;
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

    public ProductEntity asEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        List<ProductTypeEntity> productTypeEntities = new ArrayList<>();
        if (productDto.getProductTypes()==null){
            productTypeEntities=null;
        } else {
            for (ProductTypeDto productTypeDto : productDto.getProductTypes()) {
                productTypeEntities.add(
                        productTypeMapper.asEntity(productTypeDto)
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

    public ProductDto asDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        List<ProductTypeDto> productTypeDtos = new ArrayList<>();
        if (productEntity.getProductTypes()==null){
            productTypeDtos=null;
        } else {
            for (ProductTypeEntity productTypeEntity : productEntity.getProductTypes()) {
                productTypeDtos.add(
                        productTypeMapper.asDto(productTypeEntity)
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