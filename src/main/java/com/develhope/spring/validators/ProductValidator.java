package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidProductException;
import com.develhope.spring.models.dtos.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductValidator {

    public void validateProduct(ProductDto productDto) throws InvalidProductException {

        if (productDto.getName() == null && productDto.getPrice() == null) {
            throw new InvalidProductException("Product mush have a name and a price");
        }

    }

}
