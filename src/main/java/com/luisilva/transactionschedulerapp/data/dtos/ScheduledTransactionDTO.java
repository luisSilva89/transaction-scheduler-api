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
public class ScheduledTransactionDTO {


    private Long id;
    private Long clientAccountId;
    private Float amount;
    private LocalDate dueDate;
    private String transactionType;
    private Float fee;
    private String status;

}
