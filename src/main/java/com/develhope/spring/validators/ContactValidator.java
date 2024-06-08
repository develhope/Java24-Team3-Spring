package com.develhope.spring.validators;

import org.springframework.stereotype.Component;

@Component
public class ContactValidator {
    public void validateEmail(String email) throws Exception {
        String emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (!email.matches(emailPattern)) {
            throw new Exception("Invalid email address: " + email);
        }
    }

    public void validatePhoneNumber(String phoneNumber) throws Exception {
        String phonePattern = "^\\+\\d{12}$";

        if (!phoneNumber.matches(phonePattern)) {
            throw new Exception("Invalid phone number: " + phoneNumber);
        }
    }
}
