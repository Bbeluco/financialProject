package com.study.financial.handlers;

import com.study.financial.DTO.errors.IncompleteBodyParametersDTO;
import com.study.financial.exceptions.IncompleteBodyParametersException;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class TransactionsErrorHandle {

    @ExceptionHandler(IncompleteBodyParametersException.class)
    @ResponseBody
    public ResponseEntity<IncompleteBodyParametersDTO> handlerIncompleteBodyParametersException(IncompleteBodyParametersException exception){
        IncompleteBodyParametersDTO erro = new IncompleteBodyParametersDTO(exception.getMessage());

        return ResponseEntity.status(400).body(erro);
    }


}
