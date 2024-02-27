package com.luisilva.transactionschedulerapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDate;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidSchedulingDateException extends RuntimeException {

    public InvalidSchedulingDateException(LocalDate maxSchedulingDate) {
        super("The maximum scheduling date allowed for the amount submitted is " + maxSchedulingDate);
    }

    public InvalidSchedulingDateException(LocalDate minSchedulingDate, String future) {
        super("The scheduling date range allowed for the amount submitted is between " + minSchedulingDate + " and " + future);
    }

}
