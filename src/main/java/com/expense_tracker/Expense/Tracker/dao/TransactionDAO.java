package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Transaction;

public interface TransactionDAO {
    int saveTransactionDAO(Transaction transaction);
    Transaction getTransactionDAO(int transactionId);
    int modifyTransactionDetailsDAO(Transaction transaction);
    int deleteTransactionDAO(int transactionId);
}
