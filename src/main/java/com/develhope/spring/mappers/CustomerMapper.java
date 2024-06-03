package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

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
        customerEntity.setUserDetailsEntity(customerDto.getUserDetails());

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
        customerDto.setUserDetailsEntity(customerEntity.getUserDetails());

        return customerDto;
    }
}

