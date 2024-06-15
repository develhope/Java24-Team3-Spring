package com.develhope.spring.exceptions;

public class InvalidContactException extends ExceptionWithResponseCode {
    public InvalidContactException(String message) {
        super(message);
    }
}
