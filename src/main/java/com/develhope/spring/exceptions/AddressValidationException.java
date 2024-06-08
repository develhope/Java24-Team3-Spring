package com.develhope.spring.exceptions;

public class AddressValidationException extends RuntimeException {
    public AddressValidationException(String message) {
        super(message);
    }
}