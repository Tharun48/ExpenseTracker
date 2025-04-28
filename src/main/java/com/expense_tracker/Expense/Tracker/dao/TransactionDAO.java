package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.SavingsResponseDTO;
import com.expense_tracker.Expense.Tracker.model.Transaction;

import java.time.LocalDate;
import java.util.List;

public interface TransactionDAO {
    int saveTransactionDAO(Transaction transaction);
    Transaction getTransactionDAO(int transactionId);
    int modifyTransactionDetailsDAO(Transaction transaction);
    int deleteTransactionDAO(int transactionId);
    List<Transaction> savings(int userId, LocalDate fromDate, LocalDate toDate);
    List<Transaction> getTransactionUser(int userId);
    List<Transaction> getTransactionBasedOnMonthAndYear(int userId,int year,int month);
}
