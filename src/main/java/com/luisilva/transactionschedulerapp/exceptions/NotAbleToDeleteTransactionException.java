package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAbleToDeleteTransactionException extends RuntimeException {


    public NotAbleToDeleteTransactionException(String status) {
        super("Transactions with status " + status + " cannot be deleted");
    }
}
