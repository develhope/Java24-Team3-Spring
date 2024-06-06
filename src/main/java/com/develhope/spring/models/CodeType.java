package com.develhope.spring.models;

public enum CodeType {
    SUCCESS("Operazione completata con successo."),
    FAIL("Operazione fallita.");

    private String message;

    CodeType(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
