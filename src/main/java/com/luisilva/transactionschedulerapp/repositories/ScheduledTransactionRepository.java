package com.luisilva.transactionschedulerapp.repositories;

import com.luisilva.transactionschedulerapp.data.entities.ScheduledTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ScheduledTransactionRepository extends JpaRepository<ScheduledTransaction, Integer> {

    @Query("SELECT st FROM ScheduledTransaction st WHERE st.clientAccountId = :clientAccountId")
    List<ScheduledTransaction> findScheduledTransactionsByClientAccountId(@Param("clientAccountId") Long clientAccountId);

    @Query("SELECT st FROM ScheduledTransaction st WHERE st.id = :id")
    Optional<ScheduledTransaction> findById(@Param("id") Long id);
}
