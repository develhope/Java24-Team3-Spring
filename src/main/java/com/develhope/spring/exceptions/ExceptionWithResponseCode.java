package com.develhope.spring.exceptions;

import com.develhope.spring.models.ResponseCode;

public class ExceptionWithResponseCode extends Exception{
    private ResponseCode code;

    public ExceptionWithResponseCode(String message) {
        super(message);
    }

    public ExceptionWithResponseCode(String message, ResponseCode code) {
        super(message);
        this.code = code;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }
}
