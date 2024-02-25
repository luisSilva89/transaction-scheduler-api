package com.luisilva.transactionschedulerapp.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewScheduledTransactionDTO {

    private Long id;
    private Long clientAccountId;
    private double amount;
    private LocalDate dueDate;
    private String transactionType;
    private String status;

}
