package com.luisilva.transactionschedulerapp.services;


import com.luisilva.transactionschedulerapp.data.dtos.NewScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import com.luisilva.transactionschedulerapp.exceptions.InvalidSchedulingDate;
import com.luisilva.transactionschedulerapp.exceptions.NoContentAtTheDatabaseException;
import com.luisilva.transactionschedulerapp.repositories.ScheduledTransactionRepository;
import com.luisilva.transactionschedulerapp.transactionFee.TransactionFeeCalculator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final ScheduledTransactionRepository repository;
    private final ModelMapper modelMapper;

    public TransactionService(ScheduledTransactionRepository scheduledTransactionRepository, ModelMapper modelMapper) {
        this.repository = scheduledTransactionRepository;
        this.modelMapper = modelMapper;
    }

    public List<ScheduledTransactionDTO> getAllTransactionsFromClientAccount(Long clientAccountId) {

        List<ScheduledTransaction> clientScheduledTransactions = repository.findScheduledTransactionsByClientAccountId(clientAccountId);

        if (clientScheduledTransactions.isEmpty())
            throw (new NoContentAtTheDatabaseException(ScheduledTransaction.class, clientAccountId));

        return clientScheduledTransactions.stream()
                .map(st -> modelMapper.map(st, ScheduledTransactionDTO.class))
                .collect(Collectors.toList());
    }

    public void saveScheduledTransaction(NewScheduledTransactionDTO newScheduledTransactionDTO) {

        if (Objects.isNull(newScheduledTransactionDTO))
            throw new IllegalArgumentException("The request body cannot be null");

        TransactionFeeCalculator transactionFeeCalculator = new TransactionFeeCalculator(newScheduledTransactionDTO.getAmount());
        double fee = transactionFeeCalculator.calculateTransactionFee(newScheduledTransactionDTO.getAmount(), newScheduledTransactionDTO.getDueDate());

        LocalDate TODAY = LocalDate.now();
        // If the scheduling date is previous to today's throw an exception as it must be from today onwards
        if (newScheduledTransactionDTO.getDueDate().isBefore(TODAY)) {
            throw new InvalidSchedulingDate(TODAY, "any posterior date");
        }

        // If the scheduling date is for today then the transaction will be "Executed" otherwise it will be of "Pending" status
        String status = newScheduledTransactionDTO.getDueDate().equals(TODAY) ? "Executed" : "Pending";

        ScheduledTransaction scheduledTransaction = ScheduledTransaction.builder()
                .clientAccountId(newScheduledTransactionDTO.getClientAccountId())
                .transactionType(newScheduledTransactionDTO.getTransactionType())
                .amount(newScheduledTransactionDTO.getAmount())
                .dueDate(newScheduledTransactionDTO.getDueDate())
                .fee(fee)
                .status(status)
                .build();

        repository.save(scheduledTransaction);
    }

}
