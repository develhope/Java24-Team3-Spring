package com.develhope.spring.exceptions;

public class InvalidCartException extends Exception{
    public InvalidCartException(String message) {
        super(message);
    }
}
