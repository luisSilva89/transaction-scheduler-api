package com.luisilva.transactionschedulerapp.transactionFee;

import java.time.LocalDate;

public class TransactionFeeCalculator {

    private TransactionFeeStrategy feeStrategy;

    public TransactionFeeCalculator(double amount, LocalDate schedulingDate) {
        if (amount <= 1000) {
            feeStrategy = new FeeStrategyA();
        } else if (amount <= 2000) {
            feeStrategy = new FeeStrategyB();
        } else {
            feeStrategy = new FeeStrategyC();
        }
    }


}
