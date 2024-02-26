package com.luisilva.transactionschedulerapp.transactionFee;

import com.luisilva.transactionschedulerapp.exceptions.InvalidTransactionAmount;

import java.time.LocalDate;

public class TransactionFeeCalculator {

    private final TransactionFeeStrategy feeStrategy;

    private final Integer ZERO_EUROS = 0;
    private final Integer THOUSAND_EUROS = 1000;
    private final Integer TWO_THOUSAND_EUROS = 2000;

    public TransactionFeeCalculator(double amount) {
        if (amount <= ZERO_EUROS) {
            throw new InvalidTransactionAmount(amount);

        } else if (amount <= THOUSAND_EUROS) {
            feeStrategy = new FeeStrategyA();

        } else if (amount <= TWO_THOUSAND_EUROS) {
            feeStrategy = new FeeStrategyB();

        } else {
            feeStrategy = new FeeStrategyC();
        }
    }

    public double calculateTransactionFee(double amount, LocalDate schedulingDate) {
        return feeStrategy.calculateFee(amount, schedulingDate);
    }


}
