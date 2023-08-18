package com.study.financial.exceptions;

public class IncompleteBodyParametersException extends Exception{
    private String errorMessage;

    public IncompleteBodyParametersException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
