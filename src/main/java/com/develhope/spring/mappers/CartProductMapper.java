package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.CartDto;
import com.develhope.spring.models.dtos.CartProductDto;
import com.develhope.spring.models.entities.CartEntity;
import com.develhope.spring.models.entities.CartProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartProductMapper {

    private final ProductMapper productMapper;

    public CartProductMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CartProductEntity toEntity(CartProductDto cartProductDto) {
        if (cartProductDto == null) {
            return null;
        }

        CartProductEntity cartProductEntity = new CartProductEntity();

        cartProductEntity.setId(cartProductDto.getId());
        cartProductEntity.setQuantity(cartProductDto.getQuantity());
        cartProductEntity.setProduct(productMapper.toEntity(cartProductDto.getProduct()));
        cartProductEntity.setCart(cartToEntity(cartProductDto.getCart()));

        return cartProductEntity;
    }

    public CartProductDto toDTO(CartProductEntity cartProductEntity) {
        if (cartProductEntity == null) {
            return null;
        }

        CartProductDto cartProductDto = new CartProductDto();

        cartProductDto.setId(cartProductEntity.getId());
        cartProductDto.setQuantity(cartProductEntity.getQuantity());
        cartProductDto.setProduct(productMapper.toDto(cartProductEntity.getProduct()));
        cartProductDto.setCart(cartToDTO(cartProductEntity.getCart()));

        return cartProductDto;
    }

    private List<CartProductEntity> ToEntityList(List<CartProductDto> listDto) {
        if (listDto == null) {
            return null;
        }

        List<CartProductEntity> listEntity = new ArrayList<>(listDto.size());
        for (CartProductDto cartProductDto : listDto) {
            listEntity.add(toEntity(cartProductDto));
        }

        return listEntity;
    }

    private List<CartProductDto> ToDtoList(List<CartProductEntity> listEntity) {
        if (listEntity == null) {
            return null;
        }

        List<CartProductDto> listDto = new ArrayList<>(listEntity.size());
        for (CartProductEntity cartProductEntity : listEntity) {
            listDto.add(toDTO(cartProductEntity));
        }

        return listDto;
    }

    private CartEntity cartToEntity(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }

        CartEntity cartEntity = new CartEntity();

        cartEntity.setId(cartDto.getId());
        cartEntity.setCartProducts(ToEntityList(cartDto.getCartProducts()));

        return cartEntity;
    }

    private CartDto cartToDTO(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setId(cartEntity.getId());
        cartDto.setCartProducts(ToDtoList(cartEntity.getCartProducts()));

        return cartDto;
    }

}
