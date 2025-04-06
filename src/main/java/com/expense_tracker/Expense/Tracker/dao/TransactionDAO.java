package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Transaction;

public interface TransactionDAO {
    void saveTransactionDAO(Transaction transaction);
    Transaction getTransactionDAO(int transactionId);
    void modifyTransactionDetailsDAO(Transaction transaction);
    void deleteTransactionDAO(int transactionId);
}
