package com.luisilva.transactionschedulerapp.controllers;

import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/transaction")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/allTransactions")
    public List<ScheduledTransactionDTO> getAllTransactionsFromClientAccount() {
        return transactionService.getAllTransactionsFromClientAccount();
    }

    @PostMapping()
    public ResponseEntity<String> saveScheduledTransaction(@RequestBody ScheduledTransactionDTO scheduledTransactionDTO) {
        try {
            transactionService.saveScheduledTransaction(scheduledTransactionDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
