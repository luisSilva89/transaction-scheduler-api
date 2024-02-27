package com.luisilva.transactionschedulerapp.data.enums;

public enum TransactionTypeENUM {

    TRANSFER(1, "Transfer");

    private final int id;

    private final String value;

    TransactionTypeENUM(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }

}
