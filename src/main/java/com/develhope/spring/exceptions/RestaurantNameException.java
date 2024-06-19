package com.develhope.spring.exceptions;


public class RestaurantNameException extends ExceptionWithResponseCode {
    public RestaurantNameException(String message) {
        super(message);
    }
}