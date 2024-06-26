package com.develhope.spring.validators;

import com.develhope.spring.exceptions.IdException;
import com.develhope.spring.exceptions.InvalidProductException;
import com.develhope.spring.models.dtos.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductValidator {

    @Autowired
    IdValidator idValidator;

    public void validateProduct(List<ProductDto> productDtos) throws Exception {

        for(ProductDto p : productDtos){
            idValidator.noId(p.getId());

            validateProduct(p);
        }
    }

    public void validateProduct(ProductDto productDto) throws InvalidProductException {

        if (productDto.getName() == null && productDto.getPrice() == null) {
            throw new InvalidProductException("Product mush have a name and a price");
        }

    }

}
