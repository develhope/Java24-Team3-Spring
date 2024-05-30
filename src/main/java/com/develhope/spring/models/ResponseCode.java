package com.develhope.spring.models;

import org.springframework.stereotype.Component;

public enum ResponseCode {
    A(ResponseType.FAIL, "Creazione fallita a causa di parametri non validi."),
    B(ResponseType.SUCCESS,"Creazione effettuata con successo."),
    C(ResponseType.SUCCESS, "Record trovato."),
    D(ResponseType.SUCCESS, "Record non presente nel database."),

    E(ResponseType.SUCCESS, "Ricerca avvenuta con successo nel database.");

    private ResponseType responseType;
    private String responseCodeMessage;

    ResponseCode(ResponseType responseType, String responseCodeMessage) {
        this.responseType = responseType;
        this.responseCodeMessage = responseCodeMessage;
    }
}
