package com.luisilva.transactionschedulerapp.data.enums;

public enum TransactionStatusENUM {

    EXECUTED(1, "Executed"),
    PENDING(2, "Pending");

    private final long id;

    private final String value;

    TransactionStatusENUM(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public long getId() {
        return id;
    }

}
