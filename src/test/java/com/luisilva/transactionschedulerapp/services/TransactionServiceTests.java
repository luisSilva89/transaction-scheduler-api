package com.luisilva.transactionschedulerapp.services;

import com.luisilva.transactionschedulerapp.controllers.TransactionController;
import com.luisilva.transactionschedulerapp.data.dtos.NewScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.ScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.dtos.UpdateScheduledTransactionDTO;
import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import com.luisilva.transactionschedulerapp.data.enums.TransactionStatusENUM;
import com.luisilva.transactionschedulerapp.exceptions.*;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ExtendWith(MockitoExtension.class)
public class TransactionServiceTests {

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

        Mockito.when(repository.findScheduledTransactionsByClientAccountId(clientAccountId))
                .thenReturn(clientScheduledTransactions);

        // Act
        List<ScheduledTransactionDTO> result = service.getAllTransactionsFromClientAccount(clientAccountId);

        // Assert
        Assert.assertEquals(clientScheduledTransactions.size(), result.size());
    }

    @Test
    void shouldReturnAStatus200WhenSaveScheduledTransactionIsCalledWithValidData() {

        // Scenario
        Long clientAccountId = 1L;

        ScheduledTransaction scheduledTransaction = ScheduledTransaction.builder()
                .clientAccountId(clientAccountId)
                .transactionType("Transfer")
                .amount(1000)
                .dueDate(LocalDate.now())
                .build();

        NewScheduledTransactionDTO newValidScheduledTransactionDTO = NewScheduledTransactionDTO.builder()
                .clientAccountId(clientAccountId)
                .transactionType("Transfer")
                .amount(1000)
                .dueDate(LocalDate.now())
                .build();

        // Act
        service.saveScheduledTransaction(newValidScheduledTransactionDTO);

        // Verify
        Mockito.verify(repository, Mockito.times(1)).save(Mockito.any(ScheduledTransaction.class));
    }


    @Test
    void shouldThrowAnExceptionWhenSaveScheduledTransactionIsCalledWithNullObject() {
        Assert.assertThrows(IllegalArgumentException.class, () -> service.saveScheduledTransaction(null));
    }

    @Test
    void shouldThrowAnInvalidTransactionTypeExceptionWhenSaveScheduledTransactionIsCalledWithAnInvalidTransactionType() {
        // Scenario
        Long clientAccountId = 1L;

        NewScheduledTransactionDTO invalidDTO = NewScheduledTransactionDTO.builder()
                .clientAccountId(clientAccountId)
                .transactionType("Deposit")
                .amount(1000)
                .dueDate(LocalDate.now())
                .build();

        // Assert
        Assert.assertThrows(InvalidTransactionTypeException.class, () -> service.saveScheduledTransaction(invalidDTO));
    }

    @Test
    void shouldThrowAnInvalidTransactionAmountExceptionWhenSaveScheduledTransactionIsCalledWithAnInvalidAmount() {
        // Scenario
        Long clientAccountId = 1L;

        NewScheduledTransactionDTO invalidDTO = NewScheduledTransactionDTO.builder()
                .clientAccountId(clientAccountId)
                .transactionType("Transfer")
                .amount(-1000)
                .dueDate(LocalDate.now())
                .build();

        // Assert
        Assert.assertThrows(InvalidTransactionAmountException.class, () -> service.saveScheduledTransaction(invalidDTO));
    }

    @Test
    void shouldThrowAnInvalidSchedulingDateExceptionWhenSaveScheduledTransactionIsCalledWithAnInvalidSchedulingDate() {
        // Scenario
        Long clientAccountId = 1L;

        NewScheduledTransactionDTO invalidDTO = NewScheduledTransactionDTO.builder()
                .clientAccountId(clientAccountId)
                .transactionType("Transfer")
                .amount(10000)
                .dueDate(LocalDate.now())
                .build();

        // Assert
        Assert.assertThrows(InvalidSchedulingDateException.class, () -> service.saveScheduledTransaction(invalidDTO));
    }

    @Test
    void shouldThrowAnInvalidSchedulingDateExceptionWhenSaveScheduledTransactionIsCalledWithADateFromADayAlreadyPast() {
        // Scenario
        Long clientAccountId = 1L;

        NewScheduledTransactionDTO invalidDTO = NewScheduledTransactionDTO.builder()
                .clientAccountId(clientAccountId)
                .transactionType("Transfer")
                .amount(10000)
                .dueDate(LocalDate.now().minusDays(1))
                .build();

        // Assert
        Assert.assertThrows(InvalidSchedulingDateException.class, () -> service.saveScheduledTransaction(invalidDTO));
    }

    @Test
    void shouldThrowNotFoundExceptionWhenIdNotFound() {
        // Scenario
        Long id = 1L;
        Long clientAccountId = 1L;

        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        // Verify
        Assert.assertThrows(NoContentAtTheDatabaseException.class, () -> service.deleteScheduledTransaction(id, clientAccountId));
    }

    @Test
    void shouldThrowInvalidTransactionInfoExceptionWhenClientAccountIdMismatch() {
        // Scenario
        Long id = 1L;
        Long clientAccountId = 1L;

        ScheduledTransaction scheduledTransaction = ScheduledTransaction.builder()
                .id(id)
                .clientAccountId(2L)
                .status(TransactionStatusENUM.PENDING.getValue())
                .build();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(scheduledTransaction));

        // Assert
        Assert.assertThrows(InvalidTransactionInfoException.class, () -> service.deleteScheduledTransaction(id, clientAccountId));
    }

    @Test
    void shouldThrowNotAbleToDeleteTransactionExceptionWhenStatusIsExecuted() {
        // Scenario
        Long id = 1L;
        Long clientAccountId = 1L;

        ScheduledTransaction scheduledTransaction = ScheduledTransaction.builder()
                .id(id)
                .clientAccountId(clientAccountId)
                .status(TransactionStatusENUM.EXECUTED.getValue())
                .build();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(scheduledTransaction));

        // Assert
        Assert.assertThrows(NotAbleToDeleteTransactionException.class, () -> service.deleteScheduledTransaction(id, clientAccountId));
    }

    @Test
    void shouldDeleteScheduledTransactionWhenValid() {
        // Scenario
        Long id = 1L;
        Long clientAccountId = 1L;

        ScheduledTransaction scheduledTransaction = ScheduledTransaction.builder()
                .id(id)
                .clientAccountId(clientAccountId)
                .status(TransactionStatusENUM.PENDING.getValue())
                .build();

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(scheduledTransaction));

        // Act
        service.deleteScheduledTransaction(id, clientAccountId);

        // Verify
        Mockito.verify(repository, Mockito.times(1)).delete(scheduledTransaction);
    }


    @Test
    void shouldUpdateScheduledTransactionWhenValid() {
        // Scenario
        UpdateScheduledTransactionDTO updateScheduledTransactionDTO = UpdateScheduledTransactionDTO.builder()
                .id(1L)
                .clientAccountId(1L)
                .transactionType("Transfer")
                .amount(10000)
                .dueDate(LocalDate.now().plusDays(20))
                .build();

        ScheduledTransaction scheduledTransaction = ScheduledTransaction.builder()
                .id(1L)
                .clientAccountId(1L)
                .transactionType("Transfer")
                .amount(10000)
                .dueDate(LocalDate.now().plusDays(20))
                .status(TransactionStatusENUM.PENDING.getValue())
                .build();

        ScheduledTransactionDTO updatedScheduledTransactionDTO = ScheduledTransactionDTO.builder()
                .id(1L)
                .clientAccountId(1L)
                .transactionType("Transfer")
                .amount(10000)
                .dueDate(LocalDate.now().plusDays(20))
                .status(TransactionStatusENUM.PENDING.getValue())
                .build();

        Mockito.when(repository.findById(updateScheduledTransactionDTO.getId())).thenReturn(Optional.of(scheduledTransaction));
        Mockito.when(modelMapper.map(scheduledTransaction, ScheduledTransactionDTO.class))
                .thenReturn(updatedScheduledTransactionDTO);
        Mockito.when(repository.save(Mockito.any(ScheduledTransaction.class))).thenReturn(scheduledTransaction);

        // Act
        ScheduledTransactionDTO result = service.updateScheduledTransaction(updateScheduledTransactionDTO);

        // Assert
        Assert.assertNotNull(result);
        Assert.assertEquals(updatedScheduledTransactionDTO, result);
        Mockito.verify(repository, Mockito.times(1)).save(scheduledTransaction);
    }


}
