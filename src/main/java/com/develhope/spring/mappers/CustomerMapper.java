package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CustomerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    private final UserDetailsMapper userDetailsMapper;

    @Autowired
    public CustomerMapper(UserDetailsMapper userDetailsMapper) {
        this.userDetailsMapper = userDetailsMapper;
    }

    public CustomerEntity toEntity(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customerDto.getId());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setPassword(customerDto.getPassword());
        customerEntity.setIsDeleted(customerDto.getIsDeleted());
        customerEntity.setIsVerified(customerDto.getIsVerified());
        customerEntity.setUserDetails(this.userDetailsMapper.toEntity(customerDto.getUserDetails()));


        return customerEntity;
    }

    public CustomerDto toDTO(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customerEntity.getId());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setPassword(customerEntity.getPassword());
        customerDto.setIsDeleted(customerEntity.getIsDeleted());
        customerDto.setIsVerified(customerEntity.getIsVerified());
        customerDto.setUserDetails(this.userDetailsMapper.toDto(customerEntity.getUserDetails()));

        return customerDto;
    }
}

