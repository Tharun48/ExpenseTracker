package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.CompareMonthlySavingsDTO;
import com.expense_tracker.Expense.Tracker.model.SavingsResponseDTO;
import com.expense_tracker.Expense.Tracker.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDate;
import java.util.List;

public interface TransactionDetails {
    int saveTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);
    int modifyTransactionDetails(Transaction transaction);
    int deleteTransaction(int transactionId);
    SavingsResponseDTO savings(int userId, LocalDate fromDate, LocalDate toDate);
    List<Transaction> getTransactionUser(int userId);
    Page<Transaction> getTransactionUser1(int userId,Pageable pageable);
    List<Transaction> getTransactionWithDate(int userId, LocalDate fromDate, LocalDate toDate);
    CompareMonthlySavingsDTO comparingMonthlySavingsDTO(int userId, int firstYear, int firstMonth, int secondYear, int secondMonth);
}
