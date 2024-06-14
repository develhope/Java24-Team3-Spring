package com.develhope.spring.services;

import com.develhope.spring.exceptions.InvalidProductTypeException;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductTypeEntity;
import com.develhope.spring.mappers.ProductTypeMapper;
import com.develhope.spring.daos.ProductTypeDao;
import com.develhope.spring.validators.ProductTypeValidator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTypeService {

    private final ProductTypeDao productTypeDao;
    private final ProductTypeMapper productTypeMapper;
    private final ProductTypeValidator productTypeValidator;

    @Autowired
    public ProductTypeService(ProductTypeDao productTypeDao, ProductTypeMapper productTypeMapper, ProductTypeValidator productTypeValidator) {
        this.productTypeDao = productTypeDao;
        this.productTypeMapper = productTypeMapper;
        this.productTypeValidator = productTypeValidator;
    }

    /**
     * @param productTypeDto productTypeDto
     * @return a new ProductType
     */
    public ResponseModel createProductType(ProductTypeDto productTypeDto) {
        try {
            productTypeValidator.validateProductType(productTypeDto);
            ProductTypeEntity newProductType = this.productTypeMapper.toEntity(productTypeDto);
            this.productTypeDao.saveAndFlush(newProductType);
            return new ResponseModel(ResponseCode.B, this.productTypeMapper.toDto(newProductType));
        } catch (InvalidProductTypeException e) {
            return new ResponseModel(ResponseCode.A).addMessageDetails(e.getMessage());
        }
    }

    /**
     * @return all product types
     */
    public ResponseModel getAllProductTypes() {
        List<ProductTypeDto> productTypes = this.productTypeDao.findAll().stream().map(productTypeMapper::toDto).toList();
        if (productTypes.isEmpty()) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No productTypes were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, productTypes);
        }
    }

    /**
     * @param productType productType name
     * @return a list of productType found with selected parameter
     */
    public ResponseModel getProductTypeByName(String productType) {
        ProductTypeDto productTypes = this.productTypeMapper.toDto(this.productTypeDao.findByProductType(productType));
        if (productTypes == null) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("No productTypes were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, productTypes);
        }
    }

    /**
     * @effect productType is an identifier, can't be updated.
     * This method replaces the productType to be upgraded with a new one
     * @param productType        productType name
     * @param productTypeUpdates the new productType that replace the old one
     * @return a productType updated
     */
    public ResponseModel updateProductType(String productType, String productTypeUpdates) {
        ProductTypeEntity productTypeToUpdate = this.productTypeDao.findByProductType(productType);
        if (productTypeToUpdate == null) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("ProductType not found");
        } else if (productTypeUpdates != null) {
            this.productTypeDao.delete(productTypeToUpdate);
            ProductTypeEntity newProductType = new ProductTypeEntity();
            newProductType.setProductType(productTypeUpdates);
            return new ResponseModel(ResponseCode.G, productTypeMapper.toDto(this.productTypeDao.saveAndFlush(newProductType)));
        }
        return new ResponseModel(ResponseCode.A).addMessageDetails("Impossible to update, the body should not be null");
    }

    /**
     * @param productTypeName the productType name
     * @return delete a productType
     */
    @Transactional
    public ResponseModel deleteProductType(String productTypeName) {
        if (this.productTypeDao.findByProductType(productTypeName) == null) {
            return new ResponseModel(ResponseCode.D).addMessageDetails("ProductType not found");
        } else {
            productTypeDao.deleteByProductType(productTypeName);
            return new ResponseModel(ResponseCode.H).addMessageDetails("ProductType successfully deleted");
        }
    }

    /**
     * Delete all product types
     */
    public ResponseModel deleteAllProductTypes() {
        this.productTypeDao.deleteAll();
        return new ResponseModel(ResponseCode.H).addMessageDetails("All productTypes have been deleted");
    }

}
