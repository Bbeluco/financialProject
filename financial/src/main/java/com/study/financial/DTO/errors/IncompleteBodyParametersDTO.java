package com.study.financial.DTO.errors;

public class IncompleteBodyParametersDTO {
    private String message;

    public IncompleteBodyParametersDTO(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
