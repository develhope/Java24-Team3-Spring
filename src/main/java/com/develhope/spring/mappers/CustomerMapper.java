package com.develhope.spring.mappers;

import com.develhope.spring.models.dtos.CustomerDto;
import com.develhope.spring.models.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity asEntity(CustomerDto customerDTO) {
        if (customerDTO == null) {
            return null;
        }

        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setEmail(customerDTO.getEmail());
        customerEntity.setPassword(customerDTO.getPassword());
        customerEntity.setName(customerDTO.getName());
        customerEntity.setSurname(customerDTO.getSurname());
        customerEntity.setPhoneNumber(customerDTO.getPhoneNumber());
        customerEntity.setIsDeleted(customerDTO.getIsDeleted());

        return customerEntity;
    }

    public CustomerDto asDTO(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }

        CustomerDto customerDTO = new CustomerDto();

        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setPassword(customerEntity.getPassword());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setSurname(customerEntity.getSurname());
        customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());
        customerDTO.setIsDeleted(customerEntity.getIsDeleted());

        return customerDTO;
    }
}

