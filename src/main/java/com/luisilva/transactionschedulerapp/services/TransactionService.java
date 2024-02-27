package com.luisilva.transactionschedulerapp.services;


import com.luisilva.transactionschedulerapp.data.dtos.NewScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.UpdateScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import com.luisilva.transactionschedulerapp.data.enums.TransactionStatusENUM;
import com.luisilva.transactionschedulerapp.exceptions.*;
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

        // If no record was found throw 204 status
        if (clientScheduledTransactions.isEmpty())
            throw (new NoContentAtTheDatabaseException(ScheduledTransaction.class, clientAccountId));

        return clientScheduledTransactions.stream()
                .map(st -> modelMapper.map(st, ScheduledTransactionDTO.class))
                .collect(Collectors.toList());
    }

    public void saveScheduledTransaction(NewScheduledTransactionDTO newScheduledTransactionDTO) {

        if (Objects.isNull(newScheduledTransactionDTO))
            throw new IllegalArgumentException("The request body cannot be null");

        // Instantiate the transactionFeeCalculator with the correct FeeStrategy, based on the amount, and then calculate the fee
        TransactionFeeCalculator transactionFeeCalculator = new TransactionFeeCalculator(newScheduledTransactionDTO.getAmount());
        double fee = transactionFeeCalculator.calculateTransactionFee(newScheduledTransactionDTO.getAmount(), newScheduledTransactionDTO.getDueDate());

        LocalDate TODAY = LocalDate.now();
        // If the scheduling date is previous to today's throw an exception as it must be from today onwards
        if (newScheduledTransactionDTO.getDueDate().isBefore(TODAY)) {
            throw new InvalidSchedulingDateException(TODAY, "any posterior date");
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

    public void deleteScheduledTransaction(Long id, Long clientAccountId) {

        // If no record matches the id provided, return a 404 status code
        ScheduledTransaction scheduledTransaction = getScheduledTransactionOrThrowException(id);

        // If the clientAccountId provided does not match the clientAccountId from the record retrieved with the id requested, throw an exception
        if (!Objects.equals(scheduledTransaction.getClientAccountId(), clientAccountId)) {
            throw new InvalidTransactionInfoException(id, clientAccountId);
        }

        // If the transaction status is "Executed" it cannot be deleted as it has already been processed
        if (Objects.equals(scheduledTransaction.getStatus(), TransactionStatusENUM.EXECUTED.getValue()))
            throw new NotAbleToDeleteTransactionException(TransactionStatusENUM.EXECUTED.getValue());

        repository.delete(scheduledTransaction);
    }

    public ScheduledTransactionDTO updateScheduledTransaction(UpdateScheduledTransactionDTO updateScheduledTransactionDTO) {

        if (Objects.isNull(updateScheduledTransactionDTO))
            throw new IllegalArgumentException("The request body cannot be null");

        // If no record matches the id provided, return a 404 status code
        ScheduledTransaction scheduledTransaction = getScheduledTransactionOrThrowException(updateScheduledTransactionDTO.getId());

        // If the clientAccountId provided does not match the clientAccountId from the record retrieved with the id requested, throw an exception
        if (!Objects.equals(scheduledTransaction.getClientAccountId(), updateScheduledTransactionDTO.getClientAccountId())) {
            throw new InvalidTransactionInfoException(updateScheduledTransactionDTO.getId(), updateScheduledTransactionDTO.getClientAccountId());
        }

        // If the transaction status is "Executed" it cannot be updated as it has already been processed
        if (Objects.equals(scheduledTransaction.getStatus(), TransactionStatusENUM.EXECUTED.getValue()))
            throw new NotAbleToUpdateTransactionException(TransactionStatusENUM.EXECUTED.getValue());

        LocalDate TODAY = LocalDate.now();
        // If the scheduling date is previous to today's throw an exception as it must be from today onwards
        if (updateScheduledTransactionDTO.getDueDate().isBefore(TODAY)) {
            throw new InvalidSchedulingDateException(TODAY, "any posterior date");
        }

        TransactionFeeCalculator transactionFeeCalculator = new TransactionFeeCalculator(updateScheduledTransactionDTO.getAmount());
        double fee = transactionFeeCalculator.calculateTransactionFee(updateScheduledTransactionDTO.getAmount(), updateScheduledTransactionDTO.getDueDate());

        // If the scheduling date is for today then the transaction will be "Executed" otherwise it will be of "Pending" status
        String status = updateScheduledTransactionDTO.getDueDate().equals(TODAY) ? "Executed" : "Pending";

        scheduledTransaction.setTransactionType(updateScheduledTransactionDTO.getTransactionType());
        scheduledTransaction.setAmount(updateScheduledTransactionDTO.getAmount());
        scheduledTransaction.setDueDate(updateScheduledTransactionDTO.getDueDate());
        scheduledTransaction.setFee(fee);
        scheduledTransaction.setStatus(status);
        repository.save(scheduledTransaction);

        return modelMapper.map(repository.save(scheduledTransaction), ScheduledTransactionDTO.class);
    }

    /**
     * PRIVATE METHODS
     */

    private ScheduledTransaction getScheduledTransactionOrThrowException(long id) {
        return repository.findById(id).orElseThrow(() -> new NoContentAtTheDatabaseException(ScheduledTransaction.class));
    }

}
