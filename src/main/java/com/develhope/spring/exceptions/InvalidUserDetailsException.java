package com.develhope.spring.exceptions;

public class InvalidUserDetailsException extends Exception {
    public InvalidUserDetailsException(String message) {
        super(message);
    }
}
