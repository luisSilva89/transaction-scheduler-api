package com.luisilva.transactionschedulerapp.services;

import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import com.luisilva.transactionschedulerapp.exceptions.NoContentAtTheDatabaseException;
import com.luisilva.transactionschedulerapp.repositories.ScheduledTransactionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService service;

    @Mock
    private ScheduledTransactionRepository repository;

    @Mock
    private ModelMapper modelMapper;


    @Test
    void shouldThrowANoContentAtTheDatabaseExceptionWhenGetAllTransactionsFromClientAccountReturnsAnEmptyList() {

        // Scenario
        Long clientAccountId = 1L;

        List<ScheduledTransaction> clientScheduledTransactions = new ArrayList<>();
        Mockito.when(repository.findScheduledTransactionsByClientAccountId(clientAccountId)).thenReturn(clientScheduledTransactions);

        // Action and Asserts
        Assert.assertThrows(NoContentAtTheDatabaseException.class, () -> service.getAllTransactionsFromClientAccount(clientAccountId));
    }

    @Test
    void shouldReturnAListOfScheduledTransactionsWhenGetAllTransactionsFromClientAccountIsCalledAndExistsVariousTransactions() {

        // Scenario
        Long clientAccountId = 1L;

        List<ScheduledTransaction> clientScheduledTransactions = new ArrayList<>();

        ScheduledTransaction scheduledTransaction1 = ScheduledTransaction.builder()
                .id(1L)
                .clientAccountId(clientAccountId)
                .transactionType("Transfer")
                .amount(1000)
                .dueDate(LocalDate.now())
                .fee(1L)
                .status("Executed")
                .build();

        ScheduledTransaction scheduledTransaction2 = ScheduledTransaction.builder()
                .id(2L)
                .clientAccountId(clientAccountId)
                .transactionType("Transfer")
                .amount(1000)
                .dueDate(LocalDate.now())
                .fee(1L)
                .status("Executed")
                .build();

        clientScheduledTransactions.add(scheduledTransaction1);
        clientScheduledTransactions.add(scheduledTransaction2);

        Mockito.when(modelMapper.map(Mockito.any(ScheduledTransaction.class), Mockito.eq(ScheduledTransactionDTO.class)))
                .thenAnswer(invocation -> {
                    ScheduledTransaction scheduledTransaction = invocation.getArgument(0);
                    return ScheduledTransactionDTO.builder()
                            .id(scheduledTransaction.getId())
                            .clientAccountId(scheduledTransaction.getClientAccountId())
                            .transactionType(scheduledTransaction.getTransactionType())
                            .amount(scheduledTransaction.getAmount())
                            .dueDate(scheduledTransaction.getDueDate())
                            .fee(scheduledTransaction.getFee())
                            .status(scheduledTransaction.getStatus())
                            .build();
                });

        // Mocking the behavior of the repository
        Mockito.when(repository.findScheduledTransactionsByClientAccountId(clientAccountId))
                .thenReturn(clientScheduledTransactions);

        // Calling the method under test
        List<ScheduledTransactionDTO> result = service.getAllTransactionsFromClientAccount(clientAccountId);

        // Assertions
        Assert.assertEquals(clientScheduledTransactions.size(), result.size());
    }

}
