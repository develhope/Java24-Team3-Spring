package com.develhope.spring.mappers;

import com.develhope.spring.dtos.CustomerDTO;
import com.develhope.spring.entities.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public CustomerEntity asEntity(CustomerDTO customerDTO) {
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

    public CustomerDTO asDTO(CustomerEntity customerEntity) {
        if (customerEntity == null) {
            return null;
        }

        CustomerDTO customerDTO = new CustomerDTO();

        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setPassword(customerEntity.getPassword());
        customerDTO.setName(customerEntity.getName());
        customerDTO.setSurname(customerEntity.getSurname());
        customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());
        customerDTO.setIsDeleted(customerEntity.getIsDeleted());

        return customerDTO;
    }
}

