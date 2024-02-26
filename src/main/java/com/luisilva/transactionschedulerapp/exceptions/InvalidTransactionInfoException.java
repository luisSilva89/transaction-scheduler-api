package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionInfoException extends RuntimeException {

    public InvalidTransactionInfoException(Long id, Long clientAccountId) {
        super("There are no transactions with id " + id + " associated with the client account " + clientAccountId);
    }

}
