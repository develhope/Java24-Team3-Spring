package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.RestaurantEntity;

public class RestaurantMapper {
    public static RestaurantDto toDto(RestaurantEntity restaurantEntity){
        return new RestaurantDto(
                restaurantEntity.getId_user(),
                restaurantEntity.getEmail(),
                restaurantEntity.getRestaurantName(),
                restaurantEntity.getRestaurantPhoneNumber(),
                restaurantEntity.getAddressEntity(),
                restaurantEntity.getDescription(),
                restaurantEntity.getIsDeliveryAvaible(),
                restaurantEntity.getIsTakeAwayAvaible(),
                restaurantEntity.getItems(),
                restaurantEntity.getOperatingHours());
    }

    public static RestaurantEntity toEntity(RestaurantDto resDto){
        return new RestaurantEntity(
                resDto.getId_user(),
                resDto.getEmail(),
                resDto.getRestaurantName(),
                resDto.getRestaurantPhoneNumber(),
                resDto.getAddressEntity(),
                resDto.getDescription(),
                resDto.getIsDeliveryAvaible(),
                resDto.getIsTakeAwayAvaible(),
                resDto.getItems(),
                resDto.getOperatingHours());
    }
}
