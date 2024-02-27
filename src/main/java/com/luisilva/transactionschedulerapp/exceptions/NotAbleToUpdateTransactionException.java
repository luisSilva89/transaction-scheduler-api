package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotAbleToUpdateTransactionException extends RuntimeException {

    public NotAbleToUpdateTransactionException(String status) {
        super("Transactions with status " + status + " cannot be altered");
    }

}
