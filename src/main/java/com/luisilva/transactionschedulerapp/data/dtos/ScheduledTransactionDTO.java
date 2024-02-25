package com.luisilva.transactionschedulerapp.data.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private LocalDateTime dueDate;
    private String transactionType;
    private String fee;
    private String status;

}
