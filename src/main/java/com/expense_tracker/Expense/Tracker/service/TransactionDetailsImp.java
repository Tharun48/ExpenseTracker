package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.dao.TransactionDAO;
import com.expense_tracker.Expense.Tracker.model.Transaction;
import jakarta.transaction.Transactional;

public class TransactionDetailsImp implements TransactionDetails{

    TransactionDAO transactionDAO;
    TransactionDetailsImp(TransactionDAO transactionDAO){
        this.transactionDAO = transactionDAO;
    }

    @Override
    @Transactional
    public void saveTransaction(Transaction transaction) {
        transactionDAO.saveTransactionDAO(transaction);
    }

    @Override
    public Transaction getTransaction(int transactionId) {
        return transactionDAO.getTransactionDAO(transactionId);
    }

    @Override
    @Transactional
    public void modifyTransactionDetails(Transaction transaction) {
        transactionDAO.modifyTransactionDetailsDAO(transaction);
    }

    @Override
    @Transactional
    public void deleteTransaction(int transactionId) {
        transactionDAO.deleteTransactionDAO(transactionId);
    }
}
