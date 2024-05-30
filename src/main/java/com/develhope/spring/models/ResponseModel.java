package com.develhope.spring.models;

import org.springframework.stereotype.Component;


public class ResponseModel {

    private ResponseCode Code;
    private String messageDetails;
    private Object object;

    public ResponseModel(ResponseCode code, String messageDetails, Object object) {
        Code = code;
        this.messageDetails = messageDetails;
        this.object = object;
    }

    public ResponseModel(ResponseCode code, String messageDetails) {
        Code = code;
        this.messageDetails = messageDetails;
    }
}
