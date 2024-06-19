package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidRiderException;
import com.develhope.spring.models.dtos.RiderDto;
import org.springframework.stereotype.Component;

@Component
public class RiderValidator {

    public void validateRider(RiderDto riderDto) throws InvalidRiderException {
        if ((riderDto.getEmail() == null && riderDto.getPassword() == null) || riderDto.getEmail().isEmpty()) {
            throw new InvalidRiderException("Password and email fields must not be empty");
        }

        if (!riderDto.getPassword().matches("(?=.*[A-Z])(?=.*[@#$%^&+=!?])(?=.*[0-9])(?=\\S+$).{8,255}")) {
            throw new InvalidRiderException("Password must be at least 8 character long, must have at least 1 uppercase character, 1 lowercase character, 1 digit and 1 special character (@#$%^&+=!?)");
        }
    }

}