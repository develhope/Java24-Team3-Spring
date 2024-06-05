package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidProductTypeException;
import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.ProductTypeDto;
import org.springframework.stereotype.Component;

@Component
public class ProductTypeValidator {

    public void validateProductType(ProductTypeDto productTypeDto) throws InvalidProductTypeException {

        if (productTypeDto.getProductType() == null) {
            throw new InvalidProductTypeException("ProductType field must not be empty");
        }

    }

}
