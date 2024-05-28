package com.develhope.spring.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductTypeNotFoundException extends RuntimeException {

    public ProductTypeNotFoundException() {
        super();
    }

    public ProductTypeNotFoundException(String message) {
        super(message);
    }
}
