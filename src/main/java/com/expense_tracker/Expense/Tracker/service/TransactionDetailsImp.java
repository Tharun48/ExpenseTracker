package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.dao.TransactionDAO;
import com.expense_tracker.Expense.Tracker.dao.UserDAO;
import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.model.UserDetails;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TransactionDetailsImp implements TransactionDetails{

    TransactionDAO transactionDAO;
    UserDAO userDAO;
    TransactionDetailsImp(TransactionDAO transactionDAO,UserDAO userDAO){
        this.transactionDAO = transactionDAO;
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public int saveTransaction(Transaction transaction) {
        return transactionDAO.saveTransactionDAO(transaction);
    }

    @Override
    public Transaction getTransaction(int transactionId) {
        return transactionDAO.getTransactionDAO(transactionId);
    }

    @Override
    @Transactional
    public int modifyTransactionDetails(Transaction transaction) {
        return transactionDAO.modifyTransactionDetailsDAO(transaction);
    }

    @Override
    @Transactional
    public int deleteTransaction(int transactionId) {
        return transactionDAO.deleteTransactionDAO(transactionId);
    }
}
