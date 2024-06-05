package com.develhope.spring.models;


public class ResponseModel {

    private ResponseCode code;
    private String message;
    private Object object;

    public ResponseModel(ResponseCode code) {
        this.code = code;
        this.message = code.getCodeType().toString() + ": " + code.getCodeType().getMessage() + " " + code.getCodeMessage();
    }

    public ResponseModel(ResponseCode code, Object object) {
        this.code = code;
        this.message = code.getCodeType().toString() + ": " + code.getCodeType().getMessage() + " " + code.getCodeMessage();
        this.object = object;
    }

    public ResponseModel(ResponseCode code, String customizedMessage) {
        this.code = code;
        this.message = customizedMessage;
    }

    public ResponseCode getCode() {
        return code;
    }

    public void setCode(ResponseCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
