package com.study.financial.controllers;

import com.study.financial.DTO.TransactionDTO;
import com.study.financial.sevices.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transaction")
    public ResponseEntity paymentTransaction(@RequestBody TransactionDTO transactionDTO) throws Exception {
        transactionService.transaction(transactionDTO);
        return ResponseEntity.ok("OK");
    }
}
