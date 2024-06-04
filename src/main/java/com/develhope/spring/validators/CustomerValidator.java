package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidCustomerException;
import com.develhope.spring.models.dtos.CustomerDto;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public void validateCustomer(CustomerDto customerDto) throws InvalidCustomerException {

        if ((customerDto.getEmail() == null && customerDto.getPassword() == null) || customerDto.getEmail().isEmpty()) {
            throw new InvalidCustomerException("Password and email fields must not be empty");
        }

        //CAMBIARE GRANDEZZA MASSIMA PASS(255) SE NECESSARIO
        if (!customerDto.getPassword().matches("(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=.*[0-9])(?=\\S+$).{8,255}")) {
            throw new InvalidCustomerException
                    ("Password must be at least 8 character long, must have at least 1 uppercase character, 1 lowercase character, 1 digit and 1 special character (@#$%^&+=!?)");
        }
    }

}
