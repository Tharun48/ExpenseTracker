package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.repository.TransactionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void saveTransactionDAO(Transaction transaction) {
        entityManager.persist(transaction);
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
    public void modifyTransactionDetailsDAO(Transaction transaction) {
        entityManager.merge(transaction);
    }

    @Override
    public void deleteTransactionDAO(int transactionId) {
        Transaction transaction = getTransactionDAO(transactionId);
        entityManager.remove(transaction);
    }
}
