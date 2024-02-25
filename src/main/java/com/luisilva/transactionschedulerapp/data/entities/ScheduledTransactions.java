package com.luisilva.transactionschedulerapp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCHEDULED_TRANSACTIONS")
public class ScheduledTransactions {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "CLIENT_ACCOUNT_ID", unique = true)
    private long clientAccountId;

    @Column(name = "AMOUNT")
    private long amount;

    @Column(name = "DUE_DATE")
    private LocalDateTime dueDate;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "FEE")
    private long fee;

    @Column(name = "STATUS")
    private String status;


}
