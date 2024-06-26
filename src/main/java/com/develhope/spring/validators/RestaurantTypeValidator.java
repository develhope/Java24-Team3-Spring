package com.develhope.spring.validators;

import com.develhope.spring.daos.RestaurantTypeDao;
import com.develhope.spring.exceptions.IdException;
import com.develhope.spring.exceptions.IvalidRestaurantTypeException;
import com.develhope.spring.models.dtos.OperatingHoursDto;
import com.develhope.spring.models.dtos.RestaurantTypeDto;
import com.develhope.spring.models.entities.OperatingHoursEntity;
import com.develhope.spring.models.entities.RestaurantTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantTypeValidator {

    @Autowired
    RestaurantTypeDao restaurantTypeDao;

    public void validateRestaurantType(List<RestaurantTypeDto> restaurantTypeDtos) throws IvalidRestaurantTypeException {
        for (RestaurantTypeDto t : restaurantTypeDtos)

        if (!restaurantTypeDao.existsById(t.getRestaurantType())) {
            throw new IvalidRestaurantTypeException("The restaurantType " +
                    t.getRestaurantType() + " is not possible.");
        }
    }
}
