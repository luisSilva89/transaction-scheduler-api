package com.luisilva.transactionschedulerapp.services;


import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import com.luisilva.transactionschedulerapp.exceptions.NoContentAtTheDatabaseException;
import com.luisilva.transactionschedulerapp.repositories.ScheduledTransactionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

    private final ScheduledTransactionRepository repository;
    private final ModelMapper modelMapper;

    public TransactionService(ScheduledTransactionRepository scheduledTransactionRepository, ModelMapper modelMapper) {
        this.repository = scheduledTransactionRepository;
        this.modelMapper = modelMapper;
    }

    public List<ScheduledTransactionDTO> getAllTransactionsFromClientAccount() {

        List<ScheduledTransaction> scheduledTransactions = repository.findAll();

        if (scheduledTransactions.isEmpty()) throw (new NoContentAtTheDatabaseException(ScheduledTransaction.class));

        return scheduledTransactions.stream()
                .map(st -> modelMapper.map(st, ScheduledTransactionDTO.class))
                .collect(Collectors.toList());
    }

}
