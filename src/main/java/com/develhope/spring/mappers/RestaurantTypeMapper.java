package com.develhope.spring.mappers;


import com.develhope.spring.models.dtos.RestaurantTypeDto;
import com.develhope.spring.models.entities.RestaurantTypeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantTypeMapper {

    public RestaurantTypeEntity toEntity(RestaurantTypeDto restaurantTypeDto) {
        if (restaurantTypeDto == null) {
            return null;
        }

        RestaurantTypeEntity restaurantTypeEntity = new RestaurantTypeEntity();
        restaurantTypeEntity.setRestaurantType(restaurantTypeDto.getRestaurantType());

        return restaurantTypeEntity;
    }

    public RestaurantTypeDto toDto(RestaurantTypeEntity restaurantTypeEntity) {
        if (restaurantTypeEntity == null) {
            return null;
        }

        RestaurantTypeDto restaurantTypeDto = new RestaurantTypeDto();
        restaurantTypeDto.setRestaurantType(restaurantTypeEntity.getRestaurantType());

        return restaurantTypeDto;
    }

    public List<RestaurantTypeDto> toDto(List<RestaurantTypeEntity> restaurantTypeEntities) {
        if (restaurantTypeEntities == null) return null;


        List<RestaurantTypeDto> restaurantTypeDtos = new ArrayList<>();
        for (RestaurantTypeEntity r : restaurantTypeEntities) {
            restaurantTypeDtos.add(
                    this.toDto(r)
            );
        }
        return restaurantTypeDtos;
    }

    public List<RestaurantTypeEntity> toEntity(List<RestaurantTypeDto> restaurantTypeDtos) {
        if (restaurantTypeDtos == null) return null;

        List<RestaurantTypeEntity> restaurantTypeEntities = new ArrayList<>();
        for (RestaurantTypeDto r : restaurantTypeDtos) {
            restaurantTypeEntities.add(
                    this.toEntity(r)
            );
        }
        return restaurantTypeEntities;
    }

}
