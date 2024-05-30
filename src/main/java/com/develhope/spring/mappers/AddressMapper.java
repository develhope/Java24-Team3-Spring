package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.AddressDto;
import com.develhope.spring.models.dtos.OperatingHoursDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.AddressEntity;
import com.develhope.spring.models.entities.OperatingHoursEntity;
import com.develhope.spring.models.entities.RestaurantEntity;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper {
    public static AddressDto toDto(AddressEntity addressEntity){
        return new AddressDto();
    }

    public static AddressEntity toEntity(AddressDto addressDto){
        return new AddressEntity();
    }
}
