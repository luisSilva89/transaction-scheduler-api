package com.luisilva.transactionschedulerapp.transactionFee;

import java.time.LocalDate;

public class FeeStrategyC implements TransactionFeeStrategy {

    @Override
    public double calculateFee(double amount, LocalDate schedulingDate) {
        return 0;
    };
}
