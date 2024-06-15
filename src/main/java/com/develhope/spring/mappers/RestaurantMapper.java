package com.develhope.spring.mappers;

import com.develhope.spring.daos.OwnerDao;
import com.develhope.spring.models.dtos.ProductDto;
import com.develhope.spring.models.dtos.RestaurantByLocationDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.dtos.RestaurantDtoCreate;
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

    @Autowired
    RestaurantTypeMapper restaurantTypeMapper;

    @Autowired
    OwnerMapper ownerMapper;

    @Autowired
    OwnerDao ownerDao;

    public RestaurantDto toDto(RestaurantEntity restaurantEntity) {
        if (restaurantEntity == null) {
            return null;
        }

        List<ProductDto> productDtos = new ArrayList<>();
        if (restaurantEntity.getProductEntities() == null) {
            productDtos = null;
        } else {
            for (ProductEntity productEntity : restaurantEntity.getProductEntities()) {
                productDtos.add(
                        productMapper.toDto(productEntity)
                );
            }
        }


        return new RestaurantDto(
                restaurantEntity.getId_restaurant(),
                ownerMapper.toDto(restaurantEntity.getOwnerEntity()),
                restaurantEntity.getRestaurantName(),
                restaurantEntity.getRestaurantEmail(),
                restaurantEntity.getRestaurantPhoneNumber(),
                addressMapper.toDto(restaurantEntity.getAddressEntity()),
                restaurantEntity.getDescription(),
                restaurantEntity.getIsDeliveryAvailable(),
                restaurantEntity.getIsTakeAwayAvaible(),
                operatingHoursMapper.toDto(restaurantEntity.getOperatingHoursEntities()),
                restaurantTypeMapper.toDto(restaurantEntity.getRestaurantTypeEntities()),
                productDtos
        );
    }

    public RestaurantEntity toEntity(RestaurantDto resDto) {
        if (resDto == null) {
            return null;
        }

        List<ProductEntity> productEntities = new ArrayList<>();
        if (resDto.getProductDtos() == null) {
            productEntities = null;
        } else {
            for (ProductDto productDto : resDto.getProductDtos()) {
                productEntities.add(
                        productMapper.toEntity(productDto)
                );
            }
        }

        boolean delivery = resDto.getIsDeliveryAvailable() == null ? false : resDto.getIsDeliveryAvailable() ? true : false;
        boolean takeAway = resDto.getIsTakeAwayAvailable() == null ? false : resDto.getIsTakeAwayAvailable() ? true : false;

        new RestaurantEntity();
        return new RestaurantEntity(
                resDto.getId_restaurant(),
                ownerMapper.toEntity(resDto.getOwnerDto()),
                resDto.getRestaurantName(),
                resDto.getRestaurantEmail(),
                resDto.getRestaurantPhoneNumber(),
                addressMapper.toEntity(resDto.getAddressDto()),
                resDto.getDescription(),
                delivery,
                takeAway,
                operatingHoursMapper.toEntity(resDto.getOperatingHoursDtos()),
                restaurantTypeMapper.toEntity(resDto.getRestaurantTypeDtos()),
                productEntities

        );
    }

    public RestaurantDto toDto(RestaurantDtoCreate resDtocreate) {
        if (resDtocreate == null) {
            return null;
        }

        new RestaurantDto();
        return new RestaurantDto(
            null,
            ownerMapper.toDto(ownerDao.getById(resDtocreate.getId_owner())),
            resDtocreate.getRestaurantName(),
            resDtocreate.getRestaurantEmail(),
            resDtocreate.getRestaurantPhoneNumber(),
            resDtocreate.getAddressDto(),
            resDtocreate.getDescription(),
            resDtocreate.getIsDeliveryAvailable(),
            resDtocreate.getIsTakeAwayAvailable(),
            resDtocreate.getOperatingHoursDtos(),
            resDtocreate.getRestaurantTypeDtos(),
            resDtocreate.getProductDtos()
        );
    }

    public RestaurantByLocationDto toRestaurantByLocationDto(RestaurantEntity restaurantEntity) {
        if (restaurantEntity == null) {
            return null;
        }


        return new RestaurantByLocationDto(
                restaurantEntity.getId_restaurant(),
                restaurantEntity.getRestaurantName(),
                null,
                null,
                addressMapper.toDto(restaurantEntity.getAddressEntity()),
                null,
                null,
                restaurantEntity.getIsDeliveryAvailable(),
                restaurantEntity.getIsTakeAwayAvaible(),
                operatingHoursMapper.toDto(restaurantEntity.getOperatingHoursEntities())
        );
    }

}
