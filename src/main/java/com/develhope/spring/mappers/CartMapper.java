package com.develhope.spring.mappers;

import com.develhope.spring.daos.CartDao;
import com.develhope.spring.daos.CustomerDao;
import com.develhope.spring.daos.ProductDao;
import com.develhope.spring.models.dtos.CartDto;
import com.develhope.spring.models.dtos.CartProductDto;
import com.develhope.spring.models.entities.CartEntity;
import com.develhope.spring.models.entities.CartProductEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {

    private final ProductMapper productMapper;

    private final CustomerDao customerDao;

    private final CartDao cartDao;

    private final ProductDao productDao;

    @Autowired
    public CartMapper(ProductMapper productMapper, CustomerDao customerDao, CartDao cartDao, ProductDao productDao) {
        this.productMapper = productMapper;
        this.customerDao = customerDao;
        this.cartDao = cartDao;
        this.productDao = productDao;
    }

    public CartEntity toEntity(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }

        CartEntity cartEntity = new CartEntity();

        cartEntity.setId(cartDto.getId());
        cartEntity.setCartProducts(cartProductDtoListToEntity(cartDto.getCartProducts()));
        cartEntity.setCustomer(customerDao.findById(cartDto.getCustomerId()).get());


        return cartEntity;
    }

    public CartDto toDTO(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setId(cartEntity.getId());
        cartDto.setCartProducts(cartProductEntityListToDto(cartEntity.getCartProducts()));
        cartDto.setCustomerId(cartEntity.getCustomer().getId());

        return cartDto;
    }

    private CartProductEntity cartProductDtoToEntity(CartProductDto cartProductDto) {
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

    private List<CartProductEntity> cartProductDtoListToEntity(List<CartProductDto> listDto) {
        if (listDto == null) {
            return null;
        }

        List<CartProductEntity> listEntity = new ArrayList<>(listDto.size());
        for (CartProductDto cartProductDto : listDto) {
            listEntity.add(cartProductDtoToEntity(cartProductDto));
        }

        return listEntity;
    }

    private CartProductDto cartProductEntityToDto(CartProductEntity cartProductEntity) {
        if (cartProductEntity == null) {
            return null;
        }

        CartProductDto cartProductDto = new CartProductDto();

        cartProductDto.setId(cartProductEntity.getId());
        cartProductDto.setQuantity(cartProductEntity.getQuantity());
        cartProductDto.setProductId(cartProductEntity.getProduct().getId());
        cartProductDto.setCartId((cartProductEntity.getCart()).getId());

        return cartProductDto;
    }

    private List<CartProductDto> cartProductEntityListToDto(List<CartProductEntity> listEntity) {
        if (listEntity == null) {
            return null;
        }

        List<CartProductDto> listDto = new ArrayList<>(listEntity.size());
        for (CartProductEntity cartProductEntity : listEntity) {
            listDto.add(cartProductEntityToDto(cartProductEntity));
        }

        return listDto;
    }
}
