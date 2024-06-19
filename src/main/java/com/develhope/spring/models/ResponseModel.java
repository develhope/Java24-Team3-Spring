package com.develhope.spring.models;

public class ResponseModel {
    private ResponseCode code;
    private String message;
    private Object object;

    public ResponseModel(ResponseCode code) {
        this.code = code;
        setStandardMessage();
    }

    public ResponseModel(ResponseCode code, Object object) {
        this.code = code;
        setStandardMessage();
        this.object = object;
    }

    private ResponseModel setStandardMessage(){
        this.message = "CodeType: " + code.getCodeType().toString() + " - " + code.getCodeType().getMessage() + " CodeMessage: " + code.getCodeMessage();
        return this;
    }

    public ResponseModel addMessageDetails(String messageDetails) {
        this.message += " MessageDetails: " + messageDetails;
        return this;
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
