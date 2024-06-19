package com.develhope.spring.exceptions;

public class IvalidRestaurantTypeException extends ExceptionWithResponseCode{

    public IvalidRestaurantTypeException(String message) {
        super(message);
    }
}
