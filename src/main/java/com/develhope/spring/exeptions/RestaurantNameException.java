package com.develhope.spring.exeptions;

import org.springframework.stereotype.Component;


public class RestaurantNameException extends Exception {
    public RestaurantNameException(String message) {
        super(message);
    }
}