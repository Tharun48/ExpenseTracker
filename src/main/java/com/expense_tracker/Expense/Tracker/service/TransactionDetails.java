package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.Transaction;

public interface TransactionDetails {
    void saveTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);
    void modifyTransactionDetails(Transaction transaction);
    void deleteTransaction(int transactionId);
}
