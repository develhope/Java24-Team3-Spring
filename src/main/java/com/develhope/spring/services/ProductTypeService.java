package com.develhope.spring.services;

import com.develhope.spring.exceptions.InvalidProductTypeException;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.ProductTypeEntity;
import com.develhope.spring.mappers.ProductTypeMapper;
import com.develhope.spring.daos.ProductTypeDao;
import com.develhope.spring.validators.ProductTypeValidator;
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
            return new ResponseModel(ResponseCode.B, ResponseCode.B.getResponseType().toString() + ": "
                    + ResponseCode.B.getResponseType().getMessage() + " Details: "
                    + ResponseCode.B.getResponseCodeMessage(), this.productTypeMapper.toDto(newProductType));
        } catch (InvalidProductTypeException e) {
            return new ResponseModel(ResponseCode.A, ResponseCode.A.getResponseType().toString() + ": "
                    + ResponseCode.A.getResponseType().getMessage() + " Details: "
                    + ResponseCode.A.getResponseCodeMessage(), e.getMessage());
        }
    }

    /**
     * @return all product types
     */
    public ResponseModel getAllProductTypes() {
        List<ProductTypeDto> productTypes = this.productTypeDao.findAll().stream().map(productTypeMapper::toDto).toList();
        if (productTypes.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "No productTypes were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, ResponseCode.E.getResponseType().toString() + ": "
                    + ResponseCode.E.getResponseType().getMessage() + " Details: "
                    + ResponseCode.E.getResponseCodeMessage(), productTypes);
        }
    }

    /**
     * @param id productType id
     * @return a single product type
     */
    public ResponseModel getSingleProductType(Long id) {
        Optional<ProductTypeEntity> productTypeFound = this.productTypeDao.findById(id);
        if (productTypeFound.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "ProductType not found with the selected ID");
        } else {
            return new ResponseModel(ResponseCode.C, ResponseCode.C.getResponseType().toString() + ": "
                    + ResponseCode.C.getResponseType().getMessage() + " Details: "
                    + ResponseCode.C.getResponseCodeMessage(), productTypeMapper.toDto(productTypeFound.get()));
        }
    }

    /**
     * @param productType productType name
     * @return a list of productType found with selected parameter
     */
    public ResponseModel getProductTypeByName(String productType) {
        List<ProductTypeDto> productTypes = this.productTypeDao.findByProductType(productType).stream().map(productTypeMapper::toDto).toList();
        if (productTypes.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "No productTypes were found, the list may be empty");
        } else {
            return new ResponseModel(ResponseCode.E, ResponseCode.E.getResponseType().toString() + ": "
                    + ResponseCode.E.getResponseType().getMessage() + " Details: "
                    + ResponseCode.E.getResponseCodeMessage(), productTypes);
        }
    }

    /**
     * @param id                 productType id
     * @param productTypeUpdates updates for a productType
     * @return a product type updated
     */
    public ResponseModel updateProductType(Long id, ProductTypeDto productTypeUpdates) {
        Optional<ProductTypeEntity> productTypeToUpdate = this.productTypeDao.findById(id);
        if (productTypeToUpdate.isEmpty()) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "ProductType not found with the selected ID");
        } else if (productTypeUpdates != null) {
            if (productTypeUpdates.getProductType() != null) {
                productTypeToUpdate.get().setProductType(productTypeUpdates.getProductType());
            }
            return new ResponseModel(ResponseCode.G, ResponseCode.G.getResponseType().toString() + ": "
                    + ResponseCode.G.getResponseType().getMessage() + " Details: "
                    + ResponseCode.G.getResponseCodeMessage(), productTypeMapper.toDto(this.productTypeDao.saveAndFlush(productTypeToUpdate.get())));
        }
        return new ResponseModel(ResponseCode.A, ResponseCode.A.getResponseType().toString() + ": "
                + ResponseCode.A.getResponseType().getMessage() + " Details: "
                + ResponseCode.A.getResponseCodeMessage(), "Impossible to update, the body should not be null");
    }

    /**
     * @param id Delete a product type by id
     */
    public ResponseModel deleteProductType(Long id) {
        if (!this.productTypeDao.existsById(id)) {
            return new ResponseModel(ResponseCode.D, ResponseCode.D.getResponseType().toString() + ": "
                    + ResponseCode.D.getResponseType().getMessage() + " Details: "
                    + ResponseCode.D.getResponseCodeMessage(), "ProductType not found with the selected ID");
        } else {
            productTypeDao.deleteById(id);
            return new ResponseModel(ResponseCode.H, ResponseCode.H.getResponseType().toString() + ": "
                    + ResponseCode.H.getResponseType().getMessage() + " Details: "
                    + ResponseCode.H.getResponseCodeMessage(), "ProductType eliminated");
        }
    }

    /**
     * Delete all product types
     */
    public ResponseModel deleteAllProductTypes() {
        this.productTypeDao.deleteAll();
        return new ResponseModel(ResponseCode.H, ResponseCode.H.getResponseType().toString() + ": "
                + ResponseCode.H.getResponseType().getMessage() + " Details: "
                + ResponseCode.H.getResponseCodeMessage(), "All productTypes eliminated");
    }

}
