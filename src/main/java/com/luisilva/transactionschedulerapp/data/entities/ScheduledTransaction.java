package com.luisilva.transactionschedulerapp.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SCHEDULED_TRANSACTIONS")
public class ScheduledTransaction {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CLIENT_ACCOUNT_ID")
    private Long clientAccountId;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "DUE_DATE")
    private LocalDate dueDate;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "FEE")
    private double fee;

    @Column(name = "STATUS")
    private String status;

}
