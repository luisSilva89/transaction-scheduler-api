package com.luisilva.transactionschedulerapp.services;

import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import com.luisilva.transactionschedulerapp.repositories.ScheduledTransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit4.SpringRunner;

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
        List<ScheduledTransaction> clientScheduledTransactions = new ArrayList<>();


    }
}
