package com.develhope.spring.validators;

import com.develhope.spring.daos.UserDetailsDao;
import com.develhope.spring.exceptions.InvalidUserDetailsException;
import com.develhope.spring.models.dtos.UserDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDetailsValidator {

    @Autowired
    UserDetailsDao userDetailsDao;

    public void userDetailsValidator(UserDetailsDto userDetailsDto) throws InvalidUserDetailsException {
        if (userDetailsDto.getName() == null && userDetailsDto.getSurname() == null && userDetailsDto.getPhoneNumber() == null) {
            throw new InvalidUserDetailsException("Name, surname and phone number must not be empty");
        }

        if(userDetailsDao.findUserDetailsByPhoneNumber(userDetailsDto.getPhoneNumber()).isPresent()){
            throw new InvalidUserDetailsException("This phone number is already used by a user");}


        //aggiungere controllo nomi non validi/offensivi???
    }

}
