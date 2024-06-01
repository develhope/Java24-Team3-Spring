package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity asEntity(ProductDto productDTO) {
        if (productDTO == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setIngredients(productDTO.getIngredients());
        productEntity.setProductTypes(productDTO.getProductTypes());

        return productEntity;
    }

    public ProductDto asDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setIngredients(productEntity.getIngredients());
        productDto.setProductTypes(productEntity.getProductTypes());

        return productDto;
    }

}
