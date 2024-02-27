package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionTypeException extends RuntimeException {

    public InvalidTransactionTypeException() {
        super("Only transactions of type 'Transfer' are allowed.");
    }

}
