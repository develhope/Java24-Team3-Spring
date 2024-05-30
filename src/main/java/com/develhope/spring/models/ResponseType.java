package com.develhope.spring.models;

import org.springframework.stereotype.Component;

public enum ResponseType {
    SUCCESS("Operazione completata con successo."),
    FAIL("Operazione fallita.");

    private String message;

    ResponseType(String message) {
        this.message = message;
    }
}
