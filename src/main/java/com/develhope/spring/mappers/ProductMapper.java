package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity toEntity(ProductDto productDTO) {
        if (productDTO == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setIngredients(productDTO.getIngredients());
        productEntity.setProductTypes(productDTO.getProductTypes());

        return productEntity;
    }

    public ProductDto toDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();
        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setIngredients(productEntity.getIngredients());
        productDto.setProductTypes(productEntity.getProductTypes());

        return productDto;
    }

}
