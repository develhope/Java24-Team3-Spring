package com.develhope.spring.models;

import org.springframework.stereotype.Component;

public enum ResponseCode {
    A(ResponseType.FAIL, "Creazione del record fallita a causa di parametri non validi."),
    B(ResponseType.SUCCESS,"Creazione del record effettuata con successo."),
    C(ResponseType.SUCCESS, "Record trovato."),
    D(ResponseType.SUCCESS, "Record non presente nel database."),
    E(ResponseType.SUCCESS, "Ricerca nel database avvenuta con successo.");

    private ResponseType responseType;
    private String responseCodeMessage;

    ResponseCode(ResponseType responseType, String responseCodeMessage) {
        this.responseType = responseType;
        this.responseCodeMessage = responseCodeMessage;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public String getResponseCodeMessage() {
        return responseCodeMessage;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "responseType=" + responseType +
                ", responseCodeMessage='" + responseCodeMessage + '\'' +
                '}';
    }
}
