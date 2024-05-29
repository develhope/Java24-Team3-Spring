package com.develhope.spring.services;

import com.develhope.spring.dtos.ProductTypeDTO;
import com.develhope.spring.entities.ProductTypeEntity;
import com.develhope.spring.exceptions.ProductTypeNotFoundException;
import com.develhope.spring.mappers.ProductTypeMapper;
import com.develhope.spring.repositories.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final ProductTypeMapper productTypeMapper;

    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
    }

    public ProductTypeDTO createProductType(ProductTypeDTO productTypeDTO) {
        ProductTypeEntity newProductType = this.productTypeMapper.asEntity(productTypeDTO);
        this.productTypeRepository.saveAndFlush(newProductType);
        return productTypeMapper.asDto(newProductType);
    }

    public List<ProductTypeEntity> getAllProductTypes() {
        List<ProductTypeEntity> productTypes = this.productTypeRepository.findAll();
        if (productTypes.isEmpty()) {
            throw new ProductTypeNotFoundException();
        } else {
            return productTypes;
        }
    }

    public ProductTypeEntity getSingleProductType(UUID id) {
        Optional<ProductTypeEntity> productTypeFound = this.productTypeRepository.findById(id);
        if (productTypeFound.isEmpty()) {
            throw new ProductTypeNotFoundException();
        } else {
            return productTypeFound.get();
        }
    }

    public ProductTypeDTO updateProductType(UUID id, ProductTypeDTO productTypeDTO) {
        Optional<ProductTypeEntity> productTypeToUpdate = this.productTypeRepository.findById(id);
        if (productTypeToUpdate.isEmpty()) {
            throw new ProductTypeNotFoundException();
        } else {
            productTypeToUpdate.get().setProductType(productTypeDTO.getProductType());
            return productTypeMapper.asDto(this.productTypeRepository.saveAndFlush(productTypeToUpdate.get()));
        }
    }

    public void deleteProductType(UUID id) {
        if (!productTypeRepository.existsById(id)) {
            throw new ProductTypeNotFoundException();
        } else {
            productTypeRepository.deleteById(id);
        }
    }

    public void deleteAllProductTypes() {
        this.productTypeRepository.deleteAll();
    }

}
