package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSchedulingDate extends RuntimeException {

    public InvalidSchedulingDate(LocalDate maxSchedulingDate) {
        super("The maximum scheduling date for the amount submitted is " + maxSchedulingDate);
    }

    public InvalidSchedulingDate(LocalDate minSchedulingDate, LocalDate maxSchedulingDate) {
        super("The scheduling date range for the amount submitted is between " + minSchedulingDate + " and " + maxSchedulingDate);
    }
}
