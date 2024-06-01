package com.develhope.spring.services;

import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductTypeEntity;
import com.develhope.spring.exceptions.ProductTypeNotFoundException;
import com.develhope.spring.mappers.ProductTypeMapper;
import com.develhope.spring.daos.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
    }

    /**
     * @param productTypeDto
     * @return new ProductTYpe
     */
    public ProductTypeDto createProductType(ProductTypeDto productTypeDto) {
        ProductTypeEntity newProductType = this.productTypeMapper.asEntity(productTypeDto);
        this.productTypeRepository.saveAndFlush(newProductType);
        return productTypeMapper.asDto(newProductType);
    }

    /**
     * @return all product types
     */
    public List<ProductTypeDto> getAllProductTypes() {
        List<ProductTypeDto> productTypes = this.productTypeRepository.findAll().stream().map(productTypeMapper::asDto).toList();
        if (productTypes.isEmpty()) {
            return new ArrayList<>();
        } else {
            return productTypes;
        }
    }

    /**
     * @param id
     * @return a single product type
     */
    public ProductTypeDto getSingleProductType(String id) {
        Optional<ProductTypeEntity> productTypeFound = this.productTypeRepository.findById(id);
        if (productTypeFound.isEmpty()) {
            throw new ProductTypeNotFoundException();
        } else {
            return productTypeMapper.asDto(productTypeFound.get());
        }
    }

    /**
     * @param id
     * @param productTypeDTO
     * @return a product type updated
     */
    public ProductTypeDto updateProductType(String id, ProductTypeDto productTypeDTO) {
        Optional<ProductTypeEntity> productTypeToUpdate = this.productTypeRepository.findById(id);
        if (productTypeToUpdate.isEmpty()) {
            throw new ProductTypeNotFoundException();
        } else {
            productTypeToUpdate.get().setProductType(productTypeDTO.getProductType());
            return productTypeMapper.asDto(this.productTypeRepository.saveAndFlush(productTypeToUpdate.get()));
        }
    }

    /**
     * @param id Delete a product type by id
     */
    public void deleteProductType(String id) {
        if (!productTypeRepository.existsById(id)) {
            throw new ProductTypeNotFoundException();
        } else {
            productTypeRepository.deleteById(id);
        }
    }

    /**
     * Delete all product types
     */
    public void deleteAllProductTypes() {
        this.productTypeRepository.deleteAll();
    }

}
