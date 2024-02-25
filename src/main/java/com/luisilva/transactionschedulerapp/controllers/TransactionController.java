package com.luisilva.transactionschedulerapp.controllers;

import com.luisilva.transactionschedulerapp.data.dtos.TransactionDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/transaction")
public class TransactionController {

    @GetMapping("/allTransactions")
    private String getAllTransactionsFromClientAccount() {
        return "all transactions";
    }


}
