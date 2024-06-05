package com.develhope.spring.models;

public enum ResponseCode {
    A(CodeType.FAIL, "Creazione del record fallita a causa di parametri non validi."),
    B(CodeType.SUCCESS,"Creazione del record effettuata."),
    C(CodeType.SUCCESS, "Record trovato nel database."),
    D(CodeType.SUCCESS, "Record non presente nel database."),
    E(CodeType.SUCCESS, "Ricerca nel database effettuata."),
    F(CodeType.FAIL, "Non è possibile inserire l'id del record nel body."),
    G(CodeType.SUCCESS, "Modifica del record effettuata."),
    H(CodeType.SUCCESS, "Il reconrd è stato eliminato dal DB."),
    I(CodeType.SUCCESS, "Seeding completato correttamente."),
    J(CodeType.SUCCESS, "Il record nel database sono stati eliminati.");


    private CodeType codeType;
    private String codeMessage;

    ResponseCode(CodeType responseType, String responseCodeMessage) {
        this.codeType = responseType;
        this.codeMessage = responseCodeMessage;
    }

    public CodeType getCodeType() {
        return codeType;
    }

    public String getCodeMessage() {
        return codeMessage;
    }

    @Override
    public String toString() {
        return "ResponseCode{" +
                "responseType=" + codeType +
                ", responseCodeMessage='" + codeMessage + '\'' +
                '}';
    }
}
