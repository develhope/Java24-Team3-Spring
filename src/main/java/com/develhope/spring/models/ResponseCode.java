package com.develhope.spring.models;

public enum ResponseCode {

    A(CodeType.FAIL, "Creation of the record failed due to invalid parameters."),
    B(CodeType.SUCCESS, "Record creation successful."),
    C(CodeType.SUCCESS, "Record found in the database."),
    D(CodeType.SUCCESS, "Record not present in the database."),
    E(CodeType.SUCCESS, "Database search performed."),
    F(CodeType.FAIL, "Cannot insert record id in the body."),
    G(CodeType.SUCCESS, "Record modification successful."),
    H(CodeType.SUCCESS, "Record has been deleted from the DB."),
    I(CodeType.SUCCESS, "Seeding completed successfully."),
    J(CodeType.SUCCESS, "Records in the database have been deleted.");



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
