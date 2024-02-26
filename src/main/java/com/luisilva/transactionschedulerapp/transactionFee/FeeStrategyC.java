package com.luisilva.transactionschedulerapp.transactionFee;

import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDate;

import java.time.LocalDate;

public class FeeStrategyC implements TransactionFeeStrategy {

    private final LocalDate TEN_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(10);
    private final LocalDate ELEVEN_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(11);
    private final LocalDate TWENTY_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(20);
    private final LocalDate TWENTY_ONE_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(21);
    private final LocalDate THIRTY_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(30);
    private final LocalDate THIRTY_ONE_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(31);
    private final LocalDate FORTY_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(40);
    private final LocalDate FORTY_ONE_DAYS_IN_THE_FUTURE = LocalDate.now().plusDays(41);
    private final String FUTURE = "any posterior date";


    @Override
    public double calculateFee(double amount, LocalDate schedulingDate) {

        // Scheduling date is before 11 days from today's date
        if (schedulingDate.isBefore(ELEVEN_DAYS_IN_THE_FUTURE)) {
            throw new InvalidSchedulingDate(ELEVEN_DAYS_IN_THE_FUTURE, FUTURE);

            // Scheduling date is between 11 and 20 days from today's date
        } else if (schedulingDate.isAfter(TEN_DAYS_IN_THE_FUTURE) && schedulingDate.isBefore(TWENTY_ONE_DAYS_IN_THE_FUTURE)) {
            return amount * 0.082;

            // Scheduling date is between 21 and 30 days from today's date
        } else if (schedulingDate.isAfter(TWENTY_DAYS_IN_THE_FUTURE) && schedulingDate.isBefore(THIRTY_ONE_DAYS_IN_THE_FUTURE)) {
            return amount * 0.069;

            // Scheduling date is between 31 and 40 days from today's date
        } else if (schedulingDate.isAfter(THIRTY_DAYS_IN_THE_FUTURE) && schedulingDate.isBefore(FORTY_ONE_DAYS_IN_THE_FUTURE)) {
            return amount * 0.047;

            // Scheduling date is 40 days higher than today's date
        } else if (schedulingDate.isAfter(FORTY_DAYS_IN_THE_FUTURE)) {
            return amount * 0.017;

            // Scheduling date does not fall in any of the criteria mentioned before
        } else throw new IllegalArgumentException();
    }

}
