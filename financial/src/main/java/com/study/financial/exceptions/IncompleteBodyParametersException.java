package com.study.financial.exceptions;

public class IncompleteBodyParametersException extends Throwable{
    private String message;

    public IncompleteBodyParametersException(String errorMessage){
        this.message = errorMessage;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
