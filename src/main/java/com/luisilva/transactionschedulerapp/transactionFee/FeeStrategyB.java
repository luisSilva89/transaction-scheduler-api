package com.luisilva.transactionschedulerapp.transactionFee;

import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDate;

import java.time.LocalDate;

public class FeeStrategyB implements TransactionFeeStrategy {

    private final LocalDate TODAY = LocalDate.now();
    private final LocalDate TEN_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(10);

    @Override
    public double calculateFee(double amount, LocalDate schedulingDate) {

        // Scheduling date is not withing today's date and 10 days in the future
        if (!schedulingDate.isAfter(TEN_DAYS_IN_THE_FUTURE)) {
            throw new InvalidSchedulingDate(TODAY, TEN_DAYS_IN_THE_FUTURE);

        // Scheduling date is equal to today's date
        } else return amount * 0.09;
    }

}
