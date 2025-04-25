package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.dao.TransactionDAO;
import com.expense_tracker.Expense.Tracker.dao.UserDAO;
import com.expense_tracker.Expense.Tracker.model.SavingsResponseDTO;
import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.model.UserDetails;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if(transaction.getTransactionCategory()!=1 && transaction.getTransactionCategory()!=2) {
            throw new IllegalArgumentException("Invalid transaction category "+ transaction.getTransactionCategory() + " category should be 1 or 2");
        }
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

    @Override
    public SavingsResponseDTO savings(int userId, LocalDate fromDate, LocalDate toDate) {
        userDAO.getUserDAO(userId);
        List<Transaction> transactionList = transactionDAO.savings(userId,fromDate,toDate);
        double totalSavings=0;
        Map<String,Double> map = new HashMap<>();
        for(Transaction transaction:transactionList) {
            totalSavings+=(transaction.getTransactionCategory()==1) ? transaction.getTransactionAmount() : -1*transaction.getTransactionAmount();
            String description = transaction.getDescription();
            description = description.trim();
            description=description.toLowerCase();
            if(transaction.getTransactionCategory()==2) {
                if(map.containsKey(description)) {
                    map.put(description,map.get(description)+transaction.getTransactionAmount());
                }
                else{
                    map.put(description,transaction.getTransactionAmount());
                }
            }
        }
        return new SavingsResponseDTO(totalSavings,map);
    }

    @Override
    public List<Transaction> getTransactionUser(int userId) {
        return transactionDAO.getTransactionUser(userId);
    }

    @Override
    public List<Transaction> getTransactionWithDate(int userId, LocalDate fromDate, LocalDate toDate) {
        userDAO.getUserDAO(userId);
        return transactionDAO.savings(userId,fromDate,toDate);
    }


}
