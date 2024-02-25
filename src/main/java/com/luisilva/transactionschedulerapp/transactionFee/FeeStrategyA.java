package com.luisilva.transactionschedulerapp.transactionFee;

import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDate;

import java.time.LocalDate;

public class FeeStrategyA implements TransactionFeeStrategy {

    @Override
    public double calculateFee(double amount, LocalDate schedulingDate) {

        if (!schedulingDate.isEqual(LocalDate.now())) {
            throw new InvalidSchedulingDate(LocalDate.now());
        } else return amount * 0.03 + 3;
    }
}
