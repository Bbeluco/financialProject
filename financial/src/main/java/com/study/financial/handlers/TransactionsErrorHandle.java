package com.study.financial.handlers;

import com.study.financial.exceptions.IncompleteBodyParametersException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class TransactionsErrorHandle {

    @ExceptionHandler(IncompleteBodyParametersException.class)
    @ResponseBody
    public ResponseEntity<String> handlerIncompleteBodyParametersException(IncompleteBodyParametersException ex){
        return ResponseEntity.ok(ex.getErrorMessage());
    }

}
