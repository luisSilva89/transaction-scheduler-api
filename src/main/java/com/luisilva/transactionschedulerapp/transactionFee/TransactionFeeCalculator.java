package com.luisilva.transactionschedulerapp.transactionFee;

import java.time.LocalDate;

public class TransactionFeeCalculator {

    public TransactionFeeStrategy feeStrategy;

    public TransactionFeeCalculator(double amount) {
        if (amount <= 1000) {
            feeStrategy = new FeeStrategyA();
        } else if (amount <= 2000) {
            feeStrategy = new FeeStrategyB();
        } else {
            feeStrategy = new FeeStrategyC();
        }
    }

    public double calculateTransactionFee(double amount, LocalDate schedulingDate) {
        return feeStrategy.calculateFee(amount, schedulingDate);
    }


}
