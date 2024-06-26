package com.develhope.spring.mappers;

import com.develhope.spring.daos.CartDao;
import com.develhope.spring.daos.ProductDao;
import com.develhope.spring.models.dtos.CartProductDto;
import com.develhope.spring.models.entities.CartProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CartProductMapper {

    private final ProductDao productDao;

    private final CartDao cartDao;

    @Autowired
    public CartProductMapper(ProductDao productDao, CartDao cartDao) {
        this.productDao = productDao;
        this.cartDao = cartDao;
    }

    public CartProductEntity toEntity(CartProductDto cartProductDto) {
        if (cartProductDto == null) {
            return null;
        }

        CartProductEntity cartProductEntity = new CartProductEntity();

        cartProductEntity.setId(cartProductDto.getId());
        cartProductEntity.setQuantity(cartProductDto.getQuantity());
        cartProductEntity.setProduct(productDao.findById(cartProductDto.getProductId()).get());
        cartProductEntity.setCart(cartDao.findById(cartProductDto.getCartId()).get());

        return cartProductEntity;
    }

    public CartProductDto toDTO(CartProductEntity cartProductEntity) {
        if (cartProductEntity == null) {
            return null;
        }

        CartProductDto cartProductDto = new CartProductDto();

        cartProductDto.setId(cartProductEntity.getId());
        cartProductDto.setQuantity(cartProductEntity.getQuantity());
        cartProductDto.setProductId(cartProductEntity.getProduct().getId());
        cartProductDto.setCartId(cartProductEntity.getCart().getId());

        return cartProductDto;
    }


}
