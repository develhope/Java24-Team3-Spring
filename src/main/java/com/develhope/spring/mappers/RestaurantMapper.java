package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.RestaurantEntity;
import org.springframework.stereotype.Component;

@Component
public class RestaurantMapper {
    public static RestaurantDto toDto(RestaurantEntity restaurantEntity){
        if (restaurantEntity == null) {
            return null;
        }

        return new RestaurantDto(
                restaurantEntity.getId_restaurant(),
                restaurantEntity.getEmail(),
                restaurantEntity.getRestaurantName(),
                restaurantEntity.getRestaurantPhoneNumber(),
                AddressMapper.toDto( restaurantEntity.getAddressEntity()),
                restaurantEntity.getDescription(),
                restaurantEntity.getIsDeliveryAvailable(),
                restaurantEntity.getIsTakeAwayAvaible(),
                restaurantEntity.getProducts(),
                OperatingHoursMapper.toDto(restaurantEntity.getOperatingHoursEntity())
        );
    }

    public static RestaurantEntity toEntity(RestaurantDto resDto){
        if (resDto == null) {
            return null;
        }

        return new RestaurantEntity(
                resDto.getId_user(),
                resDto.getEmail(),
                resDto.getRestaurantName(),
                resDto.getRestaurantPhoneNumber(),
                AddressMapper.toEntity( resDto.getAddressDto()),
                resDto.getDescription(),
                resDto.getIsDeliveryAvailable(),
                resDto.getIsTakeAwayAvailable(),
                resDto.getProductEntities(),
                OperatingHoursMapper.toEntity(resDto.getOperatingHoursDto())
        );
    }
}
