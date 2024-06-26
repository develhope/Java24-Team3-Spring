package com.develhope.spring.models;

public class ResponseModel {
    private ResponseCode code;
    private String message;
    private Object object;

    public ResponseModel() {
    }

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
        this.message = "CodeMessage: " + code.getCodeMessage();
        return this;
    }

    public ResponseModel addMessageDetails(String messageDetails) {
        this.message += " Message details: " + messageDetails;
        return this;
    }



    public ResponseCode getCode() {
        return code;
    }

    public ResponseModel setCodeAndStdMessage(ResponseCode code) {
        this.code = code;
        setStandardMessage();
        return this;
    }

    public ResponseModel setCode(ResponseCode code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseModel setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
