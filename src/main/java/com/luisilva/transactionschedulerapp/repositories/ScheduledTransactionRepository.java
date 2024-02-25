package com.luisilva.transactionschedulerapp.repositories;

import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledTransactionRepository extends JpaRepository<ScheduledTransaction, Integer> {
}
