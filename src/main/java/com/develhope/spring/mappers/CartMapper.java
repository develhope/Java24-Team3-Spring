package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.CartDto;
import com.develhope.spring.models.dtos.CartProductDto;
import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.ProductTypeDto;
import com.develhope.spring.models.entities.CartEntity;
import com.develhope.spring.models.entities.CartProductEntity;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.models.entities.ProductTypeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartMapper {

    public CartEntity toEntity(CartDto cartDto) {
        if (cartDto == null) {
            return null;
        }

        CartEntity cartEntity = new CartEntity();

        cartEntity.setId(cartDto.getId());
        cartEntity.setCartProducts(cartProductDtoListToEntity(cartDto.getCartProducts()));

        return cartEntity;
    }

    public CartDto toDTO(CartEntity cartEntity) {
        if (cartEntity == null) {
            return null;
        }

        CartDto cartDto = new CartDto();

        cartDto.setId(cartEntity.getId());
        cartDto.setCartProducts(cartProductEntityListToDto(cartEntity.getCartProducts()));

        return cartDto;
    }

    private ProductTypeEntity productTypeDtoToEntity(ProductTypeDto productTypeDto) {
        if (productTypeDto == null) {
            return null;
        }

        ProductTypeEntity productTypeEntity = new ProductTypeEntity();

        productTypeEntity.setId(productTypeDto.getId());
        productTypeEntity.setProductType(productTypeDto.getProductType());

        return productTypeEntity;
    }

    private List<ProductTypeEntity> productTypeDtoListToEntity(List<ProductTypeDto> listDto) {
        if (listDto == null) {
            return null;
        }

        List<ProductTypeEntity> listEntity = new ArrayList<>(listDto.size());
        for (ProductTypeDto productTypeDto : listDto) {
            listEntity.add(productTypeDtoToEntity(productTypeDto));
        }

        return listEntity;
    }

    private ProductEntity productDtoToProductEntity(ProductDto productDto) {
        if (productDto == null) {
            return null;
        }

        ProductEntity productEntity = new ProductEntity();

        productEntity.setId(productDto.getId());
        productEntity.setName(productDto.getName());
        productEntity.setPrice(productDto.getPrice());
        productEntity.setIngredients(productDto.getIngredients());
        productEntity.setProductTypes(productTypeDtoListToEntity(productDto.getProductTypes()));

        return productEntity;
    }

    private CartProductEntity cartProductDtoToEntity(CartProductDto cartProductDto) {
        if (cartProductDto == null) {
            return null;
        }

        CartProductEntity cartProductEntity = new CartProductEntity();

        cartProductEntity.setId(cartProductDto.getId());
        cartProductEntity.setQuantity(cartProductDto.getQuantity());
        cartProductEntity.setProduct(productDtoToProductEntity(cartProductDto.getProduct()));
        cartProductEntity.setCart(toEntity(cartProductDto.getCart()));

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

    private ProductTypeDto productTypeEntityToDto(ProductTypeEntity productTypeEntity) {
        if (productTypeEntity == null) {
            return null;
        }

        ProductTypeDto productTypeDto = new ProductTypeDto();

        productTypeDto.setId(productTypeEntity.getId());
        productTypeDto.setProductType(productTypeEntity.getProductType());

        return productTypeDto;
    }

    private List<ProductTypeDto> productTypeEntityListToDto(List<ProductTypeEntity> listEntity) {
        if (listEntity == null) {
            return null;
        }

        List<ProductTypeDto> listDto = new ArrayList<>(listEntity.size());
        for (ProductTypeEntity productTypeEntity : listEntity) {
            listDto.add(productTypeEntityToDto(productTypeEntity));
        }

        return listDto;
    }

    private ProductDto productEntityToProductDto(ProductEntity productEntity) {
        if (productEntity == null) {
            return null;
        }

        ProductDto productDto = new ProductDto();

        productDto.setId(productEntity.getId());
        productDto.setName(productEntity.getName());
        productDto.setPrice(productEntity.getPrice());
        productDto.setIngredients(productEntity.getIngredients());
        productDto.setProductTypes(productTypeEntityListToDto(productEntity.getProductTypes()));

        return productDto;
    }

    private CartProductDto cartProductEntityToDto(CartProductEntity cartProductEntity) {
        if (cartProductEntity == null) {
            return null;
        }

        CartProductDto cartProductDto = new CartProductDto();

        cartProductDto.setId(cartProductEntity.getId());
        cartProductDto.setQuantity(cartProductEntity.getQuantity());
        cartProductDto.setProduct(productEntityToProductDto(cartProductEntity.getProduct()));
        cartProductDto.setCart(toDTO(cartProductEntity.getCart()));

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
