package com.expense_tracker.Expense.Tracker.repository;

import com.expense_tracker.Expense.Tracker.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
    Transaction findByTransactionId(int transactionId);
}
