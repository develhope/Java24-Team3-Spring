package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.AddressDto;
import com.develhope.spring.models.dtos.OperatingHoursDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.AddressEntity;
import com.develhope.spring.models.entities.OperatingHoursEntity;
import com.develhope.spring.models.entities.RestaurantEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class AddressMapper {

    public static AddressDto toDto(AddressEntity addressEntity){
        if (addressEntity == null) {
            return null;
        }

        return new AddressDto(
                addressEntity.getId_address(),
                addressEntity.getCountry(),
                addressEntity.getPostcode(),
                addressEntity.getCity(),
                addressEntity.getStreet(),
                addressEntity.getNumber(),
                addressEntity.getNote(),
                new BigDecimal[]{addressEntity.getLat(), addressEntity.getLon()}
        );
    }

    public static AddressEntity toEntity(AddressDto addressDto){
        if (addressDto == null) {
            return null;
        }

        return new AddressEntity(
                addressDto.getId_address(),
                addressDto.getCountry(),
                addressDto.getPostcode(),
                addressDto.getCity(),
                addressDto.getStreet(),
                addressDto.getNumber(),
                addressDto.getNote(),
                addressDto.getCoordinates()[0], addressDto.getCoordinates()[1]
        );
    }
}
