package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.dao.TransactionDAO;
import com.expense_tracker.Expense.Tracker.dao.UserDAO;
import com.expense_tracker.Expense.Tracker.model.CompareMonthlySavingsDTO;
import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.model.UserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserImp implements User{

    UserDAO userDAO;
    TransactionDAO transactionDAO;
    @Autowired
    UserImp(UserDAO userDAO,TransactionDAO transactionDAO){
        this.userDAO=userDAO;
        this.transactionDAO=transactionDAO;
    }


    @Override
    @Transactional
    public int saveUser(UserDetails user) {
        return userDAO.saveUserDAO(user);
    }

    @Override
    public UserDetails getUser(int userId) {
        return userDAO.getUserDAO(userId);
    }

    @Override
    @Transactional
    public int modifyUserDetails(UserDetails user) {
        return userDAO.modifyUserDetailsDAO(user);
    }

    @Override
    @Transactional
    public int deleteUser(int userId) {
        return userDAO.deleteUserDAO(userId);
    }

    @Override
    public CompareMonthlySavingsDTO comparingMonthlySavingsDTO(int userId, LocalDate firstMonthStart, LocalDate firstMonthEnd, LocalDate secondMonthStart, LocalDate secondMonthEnd) {
        List<Transaction> firstMonth = transactionDAO.savings(userId,firstMonthStart,firstMonthEnd);
        List<Transaction> secondMonth = transactionDAO.savings(userId,secondMonthStart,secondMonthEnd);
        Map<String,double[]> map = new HashMap<>();
        List<List<Transaction>> transactionList = new ArrayList<>();
        transactionList.add(firstMonth);
        transactionList.add(secondMonth);
        int size = transactionList.size();
        for(int i=0;i<size;i++) {
            List<Transaction> transactions = transactionList.get(i);
            for(Transaction transaction : transactions ) {
                int transactionCategory = transaction.getTransactionCategory();
                if(transactionCategory==1) continue;
                String transactionName = transaction.getDescription();
                transactionName = transactionName.trim().toLowerCase();
                double amount=transaction.getTransactionAmount();
                if(!map.containsKey(transactionName)) {
                    double[] monthlyAmount = new double[size];
                    monthlyAmount[i]=amount;
                    map.put(transactionName,monthlyAmount);
                }
                else{
                    double[] monthlyAmount = map.get(transactionName);
                    monthlyAmount[i]+=amount;
                    map.put(transactionName,monthlyAmount);
                }
            }
        }
        return new CompareMonthlySavingsDTO(map);
    }


}
