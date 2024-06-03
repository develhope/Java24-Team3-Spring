package com.develhope.spring.validators;

import com.develhope.spring.exceptions.InvalidUserDetailsException;
import com.develhope.spring.models.dtos.UserDetailsDto;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsValidator {

    public void userDetailsValidator(UserDetailsDto userDetailsDto) throws InvalidUserDetailsException {
        if (userDetailsDto.getName() == null && userDetailsDto.getSurname() == null && userDetailsDto.getPhoneNumber() == null) {
            throw new InvalidUserDetailsException("Name, surname and phone number must not be empty");
        }

        //aggiungere controllo nomi non validi/offensivi???
    }

}
