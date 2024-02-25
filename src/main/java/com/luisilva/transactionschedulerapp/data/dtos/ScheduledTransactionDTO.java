package com.luisilva.transactionschedulerapp.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduledTransactionDTO {


    private Long id;
    private Long clientAccountId;
    private Long amount;
    private LocalDateTime dueDate;
    private String transactionType;
    private Long fee;
    private String status;

}
