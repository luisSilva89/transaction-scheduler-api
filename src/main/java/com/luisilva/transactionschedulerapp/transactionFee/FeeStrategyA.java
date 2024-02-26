package com.luisilva.transactionschedulerapp.transactionFee;

import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDateException;

import java.time.LocalDate;

public class FeeStrategyA implements TransactionFeeStrategy {

    private final LocalDate TODAY = LocalDate.now();
    private final double TAX = 0.03;
    private final Integer ADDITIONAL_TAX = 3;

    @Override
    public double calculateFee(double amount, LocalDate schedulingDate) {

            // Scheduling date is different from today's date
        if (!schedulingDate.isEqual(TODAY)) {
            throw new InvalidSchedulingDateException(TODAY);

            // Scheduling date is equal to today's date
        } else return amount * TAX + ADDITIONAL_TAX;
    }

}
