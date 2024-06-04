package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.ProductEntity;
import com.develhope.spring.models.entities.RestaurantEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantMapper {

    @Autowired
    ProductMapper productMapper;

    @Autowired
    OperatingHoursMapper operatingHoursMapper;

    @Autowired
    AddressMapper addressMapper;

    public RestaurantDto toDto(RestaurantEntity restaurantEntity){
        if (restaurantEntity == null) {
            return null;
        }

        List<ProductDto> productDtos = new ArrayList<>();
        if (restaurantEntity.getProductEntities()==null){
            productDtos=null;
        } else {
            for (ProductEntity productEntity : restaurantEntity.getProductEntities()) {
                productDtos.add(
                    productMapper.toDto(productEntity)
                );
            }
        }

        return new RestaurantDto(
                restaurantEntity.getId_restaurant(),
                restaurantEntity.getEmail(),
                restaurantEntity.getRestaurantName(),
                restaurantEntity.getRestaurantPhoneNumber(),
                addressMapper.toDto( restaurantEntity.getAddressEntity()),
                restaurantEntity.getDescription(),
                restaurantEntity.getIsDeliveryAvailable(),
                restaurantEntity.getIsTakeAwayAvaible(),
                productDtos,
                operatingHoursMapper.toDto(restaurantEntity.getOperatingHoursEntity())
        );
    }

    public RestaurantEntity toEntity(RestaurantDto resDto){
        if (resDto == null) {
            return null;
        }

        List<ProductEntity> productEntities = new ArrayList<>();
        if (resDto.getProductDtos()==null){
            productEntities=null;
        } else {
            for (ProductDto productDto : resDto.getProductDtos()) {
                productEntities.add(
                        productMapper.toEntity(productDto)
                );
            }
        }

        boolean delivery =  resDto.getIsDeliveryAvailable() == null ? false: resDto.getIsDeliveryAvailable() ? true : false;
        boolean takeAway =  resDto.getIsTakeAwayAvailable() == null ? false: resDto.getIsTakeAwayAvailable() ? true : false;


        return new RestaurantEntity(
                resDto.getId_restaurant(),
                resDto.getEmail(),
                resDto.getRestaurantName(),
                resDto.getRestaurantPhoneNumber(),
                addressMapper.toEntity( resDto.getAddressDto()),
                resDto.getDescription(),
                delivery,
                takeAway,
                productEntities,
                operatingHoursMapper.toEntity(resDto.getOperatingHoursDto())
        );
    }
}
