package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.CartDto;
import com.develhope.spring.models.dtos.CartProductDto;
import com.develhope.spring.models.entities.CartEntity;
import com.develhope.spring.models.entities.CartProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {

    private final CartProductMapper cartProductMapper;

    public CartMapper(CartProductMapper cartProductMapper) {
        this.cartProductMapper = cartProductMapper;
    }

    public CartEntity toEntity(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }

        CartEntity cartEntity = new CartEntity();

        cartEntity.setId(cartDto.getId());
        cartEntity.setCartProducts(ToEntityList(cartDto.getCartProducts()));

        return cartEntity;
    }

    public CartDto toDTO(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setId(cartEntity.getId());
        cartDto.setCartProducts(ToDtoList(cartEntity.getCartProducts()));

        return cartDto;
    }

    private List<CartProductEntity> ToEntityList(List<CartProductDto> listDto) {
        if (listDto == null) {
            return null;
        }

        List<CartProductEntity> listEntity = new ArrayList<>(listDto.size());
        for (CartProductDto cartProductDto : listDto) {
            listEntity.add(cartProductMapper.toEntity(cartProductDto));
        }

        return listEntity;
    }

    private List<CartProductDto> ToDtoList(List<CartProductEntity> listEntity) {
        if (listEntity == null) {
            return null;
        }

        List<CartProductDto> listDto = new ArrayList<>(listEntity.size());
        for (CartProductEntity cartProductEntity : listEntity) {
            listDto.add(cartProductMapper.toDTO(cartProductEntity));
        }

        return listDto;
    }

}
