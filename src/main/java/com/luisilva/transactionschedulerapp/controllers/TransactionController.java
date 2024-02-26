package com.luisilva.transactionschedulerapp.controllers;

import com.luisilva.transactionschedulerapp.data.dtos.NewScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.UpdateScheduledTransactionDTO;
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

    @GetMapping("/allTransactions/{clientAccountId}")
    public List<ScheduledTransactionDTO> getAllTransactionsFromClientAccount(@PathVariable("clientAccountId") Long clientAccountId) {
        return transactionService.getAllTransactionsFromClientAccount(clientAccountId);
    }

    @PostMapping()
    public ResponseEntity<String> saveScheduledTransaction(@RequestBody NewScheduledTransactionDTO newScheduledTransactionDTO) {
        try {
            transactionService.saveScheduledTransaction(newScheduledTransactionDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping()
    public ScheduledTransactionDTO updateScheduledTransaction(@RequestBody UpdateScheduledTransactionDTO updateScheduledTransactionDTO) {
        return transactionService.updateScheduledTransaction(updateScheduledTransactionDTO);
    }

    @DeleteMapping("/{id}/client-account/{clientAccountId}")
    public ResponseEntity<String> deleteScheduledTransaction(@PathVariable("id") Long id, @PathVariable("clientAccountId") Long clientAccountId) {
        try {
            transactionService.deleteScheduledTransaction(id, clientAccountId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
