package com.luisilva.transactionschedulerapp.transactionFee;

import java.time.LocalDate;

public interface TransactionFeeStrategy {

    double calculateFee(double amount, LocalDate schedulingDate);
}
