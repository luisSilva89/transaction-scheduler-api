package com.luisilva.transactionschedulerapp.transactionFee;

import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDate;

import java.time.LocalDate;

public class FeeStrategyB implements TransactionFeeStrategy {

    @Override
    public double calculateFee(double amount, LocalDate schedulingDate) {

        if (!schedulingDate.isAfter(LocalDate.now().plusDays(11))) {
            throw new InvalidSchedulingDate(LocalDate.now(), LocalDate.now().plusDays(10));
        } else return amount * 0.09;
    }

}
