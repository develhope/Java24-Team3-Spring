package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity asEntity(CustomerDto customerDto) {
        if (customerDto == null) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setId(customerDto.getId());
        customerEntity.setEmail(customerDto.getEmail());
        customerEntity.setPassword(customerDto.getPassword());
        customerEntity.setName(customerDto.getName());
        customerEntity.setSurname(customerDto.getSurname());
        customerEntity.setBirthDate(customerDto.getBirthDate());
        customerEntity.setPhoneNumber(customerDto.getPhoneNumber());
        customerEntity.setCreationDate(customerDto.getCreationDate());
        customerEntity.setUpdateDate(customerDto.getUpdateDate());
        customerEntity.setIsDeleted(customerDto.getIsDeleted());
        customerEntity.setIsVerified(customerDto.getIsVerified());

        return customerEntity;
    }

    public CustomerDto asDTO(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }

        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customerEntity.getId());
        customerDto.setEmail(customerEntity.getEmail());
        customerDto.setPassword(customerEntity.getPassword());
        customerDto.setName(customerEntity.getName());
        customerDto.setSurname(customerEntity.getSurname());
        customerDto.setBirthDate(customerEntity.getBirthDate());
        customerDto.setPhoneNumber(customerEntity.getPhoneNumber());
        customerDto.setCreationDate(customerEntity.getCreationDate());
        customerDto.setUpdateDate(customerEntity.getUpdateDate());
        customerDto.setIsDeleted(customerEntity.getIsDeleted());
        customerDto.setIsVerified(customerEntity.getIsVerified());

        return customerDto;
    }
}

