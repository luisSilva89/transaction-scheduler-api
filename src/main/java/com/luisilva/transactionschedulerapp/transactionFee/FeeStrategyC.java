package com.luisilva.transactionschedulerapp.transactionFee;

import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDate;

import java.time.LocalDate;

public class FeeStrategyC implements TransactionFeeStrategy {

    @Override
    public double calculateFee(double amount, LocalDate schedulingDate) {

        LocalDate elevenDaysInTheFuture = LocalDate.now().plusDays(11);
        LocalDate twentyDaysInTheFuture = LocalDate.now().plusDays(20);
        LocalDate twentyOneDaysInTheFuture = LocalDate.now().plusDays(21);
        LocalDate thirtyDaysInTheFuture = LocalDate.now().plusDays(30);
        LocalDate thirtyOneDaysInTheFuture = LocalDate.now().plusDays(31);
        LocalDate fortyDaysInTheFuture = LocalDate.now().plusDays(40);
        LocalDate fortyOneDaysInTheFuture = LocalDate.now().plusDays(41);


        if (schedulingDate.isBefore(elevenDaysInTheFuture)) {
            // TODO: review exception
            throw new InvalidSchedulingDate(elevenDaysInTheFuture, fortyDaysInTheFuture);
        } else if (schedulingDate.isAfter(elevenDaysInTheFuture) && schedulingDate.isBefore(twentyOneDaysInTheFuture)) {
            return amount * 0.082;
        } else if (schedulingDate.isAfter(twentyDaysInTheFuture) && schedulingDate.isBefore(thirtyOneDaysInTheFuture)) {
            return amount * 0.069;
        } else if (schedulingDate.isAfter(thirtyDaysInTheFuture) && schedulingDate.isBefore(fortyOneDaysInTheFuture)) {
            return amount * 0.047;
        } else if (schedulingDate.isAfter(fortyDaysInTheFuture)) {
            return amount * 0.017;
        } else throw new IllegalArgumentException();
    }

}
