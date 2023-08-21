package com.study.financial.exceptions;

public class ThirdPartServiceDoesNotAuthorizeTransfer extends Exception{
    public ThirdPartServiceDoesNotAuthorizeTransfer(String message) {
        super(message);
    }
}
