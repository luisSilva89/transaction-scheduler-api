package com.luisilva.transactionschedulerapp.services;


import com.luisilva.transactionschedulerapp.data.dtos.NewScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import com.luisilva.transactionschedulerapp.exceptions.NoContentAtTheDatabaseException;
import com.luisilva.transactionschedulerapp.repositories.ScheduledTransactionRepository;
import com.luisilva.transactionschedulerapp.transactionFee.TransactionFeeCalculator;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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

        List<ScheduledTransaction> scheduledTransactions = repository.findScheduledTransactionsByClientAccountId(clientAccountId);

        if (scheduledTransactions.isEmpty())
            throw (new NoContentAtTheDatabaseException(ScheduledTransaction.class, clientAccountId));

        return scheduledTransactions.stream()
                .map(st -> modelMapper.map(st, ScheduledTransactionDTO.class))
                .collect(Collectors.toList());
    }


    public void saveScheduledTransaction(NewScheduledTransactionDTO newScheduledTransactionDTO) {

        if (Objects.isNull(newScheduledTransactionDTO)) throw new IllegalArgumentException();

        TransactionFeeCalculator transactionFeeCalculator = new TransactionFeeCalculator(newScheduledTransactionDTO.getAmount());

        ScheduledTransaction scheduledTransaction = ScheduledTransaction.builder()
                .clientAccountId(newScheduledTransactionDTO.getClientAccountId())
                .transactionType(newScheduledTransactionDTO.getTransactionType())
                .amount(newScheduledTransactionDTO.getAmount())
                .dueDate(newScheduledTransactionDTO.getDueDate())
                .fee(transactionFeeCalculator.calculateTransactionFee(newScheduledTransactionDTO.getAmount(), newScheduledTransactionDTO.getDueDate()))
                .status(newScheduledTransactionDTO.getStatus())
                .build();
        System.out.println(scheduledTransaction);

        repository.save(scheduledTransaction);
    }

}
