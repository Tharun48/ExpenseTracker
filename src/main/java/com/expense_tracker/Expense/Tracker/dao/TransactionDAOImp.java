package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.SavingsResponseDTO;
import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class TransactionDAOImp implements TransactionDAO{

    EntityManager entityManager;
    TransactionRepository transactionRepository;

    @Autowired
    TransactionDAOImp(EntityManager entityManager, TransactionRepository transactionRepository){
        this.entityManager=entityManager;
        this.transactionRepository=transactionRepository;
    }

    @Override
    public int saveTransactionDAO(Transaction transaction) {
        transaction.setCreatedOn(LocalDate.now());
        transaction.setTransactionAmount(Math.abs(transaction.getTransactionAmount()));
        Transaction transaction1 = entityManager.merge(transaction);
        return transaction1.getTransactionId();
    }

    @Override
    public Transaction getTransactionDAO(int transactionId) {
        Transaction transaction = transactionRepository.findByTransactionId(transactionId);
        if(transaction==null) {
            throw new IllegalArgumentException("Transaction details not found for the transactionId: " + transactionId);
        }
        return transaction;
    }

    @Override
    public int modifyTransactionDetailsDAO(Transaction transaction) {
        transaction.setCreatedOn(LocalDate.now());
        transaction.setTransactionAmount(Math.abs(transaction.getTransactionAmount()));
        entityManager.merge(transaction);
        return transaction.getTransactionId();
    }

    @Override
    public int deleteTransactionDAO(int transactionId) {
        Transaction transaction = getTransactionDAO(transactionId);
        entityManager.remove(transaction);
        return transactionId;
    }

    @Override
    public List<Transaction> savings(int userId, LocalDate fromDate, LocalDate toDate) {
        return transactionRepository.findTransactionByUserIdAndDataRange(userId,fromDate,toDate);
    }
}
