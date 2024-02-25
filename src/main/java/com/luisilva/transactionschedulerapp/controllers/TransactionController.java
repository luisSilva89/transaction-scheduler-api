package com.luisilva.transactionschedulerapp.controllers;

import com.luisilva.transactionschedulerapp.data.dtos.TransactionDTO;
import com.luisilva.transactionschedulerapp.services.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/transaction")
public class TransactionController {

    TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/allTransactions")
    private String getAllTransactionsFromClientAccount() {
        return transactionService.getAllTransactionsFromClientAccount();
    }


}
