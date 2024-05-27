package com.develhope.spring.exceptions;

public class ProductTypeNotFoundException extends RuntimeException {

    public ProductTypeNotFoundException() {
        super();
    }

    public ProductTypeNotFoundException(String message) {
        super(message);
    }
}
