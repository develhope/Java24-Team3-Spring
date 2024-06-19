package com.develhope.spring.validators;

import com.develhope.spring.exceptions.AddressValidationException;
import com.develhope.spring.models.dtos.AddressDto;
import com.develhope.spring.services.OkHttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AddressValidator {

    @Autowired
    OkHttpClientService okHttpClientService;

    public AddressDto getValidAddress(AddressDto addressDto) throws Exception {
        if (addressDto.getId_address() != null) {
            throw new AddressValidationException("ID must be null");
        }
        if (addressDto.getCoordinates()[0] != null || addressDto.getCoordinates()[1] != null) {
            throw new AddressValidationException("Latitude and Longitude must be null");
        }

        if (addressDto.getCountry() == null || addressDto.getPostcode() == null ||
                addressDto.getCity() == null || addressDto.getStreet() == null ||
                addressDto.getNumber() == null) {
            throw new AddressValidationException("Address fields country, postcode, city, street and number must not be null");
        }

        addressDto.setCountry(addressDto.getCountry().trim());
        addressDto.setPostcode(addressDto.getPostcode().trim());
        addressDto.setCity(addressDto.getCity().trim());
        addressDto.setStreet(addressDto.getStreet().trim());

        return okHttpClientService.getAddressDtoWithCoordinates(addressDto);
    }


}
