package com.develhope.spring.mappers;

import com.develhope.spring.dtos.ProductDTO;
import com.develhope.spring.entities.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public ProductEntity asEntity(ProductDTO productDTO) {
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

    public ProductDTO asDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(productEntity.getName());
        productDTO.setPrice(productEntity.getPrice());
        productDTO.setIngredients(productEntity.getIngredients());
        productDTO.setProductTypes(productEntity.getProductTypes());

        return productDTO;
    }

}
