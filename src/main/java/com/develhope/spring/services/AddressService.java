package com.develhope.spring.services;

import com.develhope.spring.daos.AddressDao;
import com.develhope.spring.mappers.AddressMapper;
import com.develhope.spring.models.ResponseCode;
import com.develhope.spring.models.ResponseModel;
import com.develhope.spring.models.dtos.AddressDto;
import com.develhope.spring.models.dtos.RestaurantDto;
import com.develhope.spring.models.entities.*;
import com.develhope.spring.validators.AddressValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressValidator addressValidator;

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressDao addressDao;

    public AddressDto createAddress(AddressDto addressDto) throws Exception {
        AddressDto validAddressDto = addressValidator.getValidAddress(addressDto);
        AddressEntity addressEntitySaved = addressDao.save(addressMapper.toEntity(validAddressDto));
        return addressMapper.toDto(addressEntitySaved);
    }

    public AddressDto updateAddress(AddressDto addressDto) {
        return null;
    }

    public AddressDto deleteAddressById(AddressDto addressDto) {
        return null;

    }


}
