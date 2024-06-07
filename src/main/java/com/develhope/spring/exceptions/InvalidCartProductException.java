package com.develhope.spring.exceptions;

public class InvalidCartProductException extends Exception{
    public InvalidCartProductException(String message) {
        super(message);
    }
}
