package com.develhope.spring.validators;

import com.develhope.spring.models.dtos.AdminDto;
import com.develhope.spring.exceptions.InvalidAdminException;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {

    public void validateAdmin(AdminDto adminDTO) throws InvalidAdminException {
        if ((adminDTO.getEmail() == null && adminDTO.getPassword() == null) || adminDTO.getEmail().isEmpty()) {
            throw new InvalidAdminException("Email and password fields can't be empty");
        }

    }
}
