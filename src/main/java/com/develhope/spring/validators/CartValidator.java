package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidCartException;
import com.develhope.spring.models.dtos.CartDto;
import org.springframework.stereotype.Component;

@Component
public class CartValidator {

    public void validateCart(CartDto cartDto) throws InvalidCartException {
        if(cartDto.getCartProducts() == null && cartDto.getCartProducts().isEmpty()) {
            throw new InvalidCartException("The list can't be null or empty");
        }
    }
}
