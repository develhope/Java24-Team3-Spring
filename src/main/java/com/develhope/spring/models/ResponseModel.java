package com.develhope.spring.models;


public class ResponseModel {

    private ResponseCode code;
    private String message;
    private Object object;

    public ResponseModel(ResponseCode code, String messageDetails, Object object) {
        this.code = code;
        this.message = messageDetails;
        this.object = object;
    }

    public ResponseModel(ResponseCode code, String messageDetails) {
        this.code = code;
        this.message = messageDetails;
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
