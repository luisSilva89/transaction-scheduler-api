package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransactionAmount extends RuntimeException {

    public InvalidTransactionAmount(double amount) {
        super("The amount submitted, " + amount + "€, must be superior than 0€");
    }

}
