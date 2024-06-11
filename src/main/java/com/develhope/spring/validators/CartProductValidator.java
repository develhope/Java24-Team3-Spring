package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidCartProductException;
import com.develhope.spring.models.dtos.CartProductDto;
import org.springframework.stereotype.Component;

@Component
public class CartProductValidator {

    public void validateCartProduct(CartProductDto cartProductDto) throws InvalidCartProductException {
        if (cartProductDto.getCartId() == null
                && cartProductDto.getProductId() == null
                && cartProductDto.getQuantity() < 0) {
            throw new InvalidCartProductException("Cart product requires a valid cart, product, and a quantity > 0.");
        }
    }
}
