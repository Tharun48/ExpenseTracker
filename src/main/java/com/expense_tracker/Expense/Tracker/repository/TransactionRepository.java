package com.expense_tracker.Expense.Tracker.repository;

import com.expense_tracker.Expense.Tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByTransactionId(int transactionId);
    @Query("SELECT t FROM Transaction where t.user_id=:userId and t.date BETWEEN :fromDate and :toDate")
    List<Transaction> findTransactionByUserIdAndDataRange(@Param("userId") int userId, @Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
