package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {

    private final ProductTypeMapper productTypeMapper;

    public ProductMapper(ProductTypeMapper productTypeMapper) {
        this.productTypeMapper = productTypeMapper;
    }

    public ProductEntity toEntity(ProductDto productDTO) {
        if (productDTO == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setIngredients(productDTO.getIngredients());
        productEntity.setProductTypes(productTypeDtoToEntity(productDTO.getProductTypes()));

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
        productDto.setProductTypes(productTypeEntityToDto(productEntity.getProductTypes()));

        return productDto;
    }

    private List<ProductTypeEntity> productTypeDtoToEntity(List<ProductTypeDto> listDto) {
        if (listDto == null) {
            return null;
        }

        List<ProductTypeEntity> listEntity = new ArrayList<>(listDto.size());
        for (ProductTypeDto productTypeDto : listDto) {
            listEntity.add(productTypeMapper.toEntity(productTypeDto));
        }

        return listEntity;
    }

    private List<ProductTypeDto> productTypeEntityToDto(List<ProductTypeEntity> listEntity) {
        if (listEntity == null) {
            return null;
        }

        List<ProductTypeDto> listDto = new ArrayList<>(listEntity.size());
        for (ProductTypeEntity productTypeEntity : listEntity) {
            listDto.add(productTypeMapper.toDto(productTypeEntity));
        }

        return listDto;
    }

}
