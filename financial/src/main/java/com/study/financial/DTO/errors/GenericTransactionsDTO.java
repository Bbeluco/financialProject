package com.study.financial.DTO.errors;

public class GenericTransactionsDTO {
    private String message;

    public GenericTransactionsDTO(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
