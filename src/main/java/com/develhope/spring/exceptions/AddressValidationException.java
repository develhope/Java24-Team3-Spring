package com.develhope.spring.exceptions;

public class AddressValidationException extends ExceptionWithResponseCode {
    public AddressValidationException(String message) {
        super(message);
    }
}