package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.Transaction;

public interface TransactionDetails {
    int saveTransaction(Transaction transaction);
    Transaction getTransaction(int transactionId);
    int modifyTransactionDetails(Transaction transaction);
    int deleteTransaction(int transactionId);
}
