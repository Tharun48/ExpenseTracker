package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.model.UserDetails;
import com.expense_tracker.Expense.Tracker.repository.TransactionRepository;
import com.expense_tracker.Expense.Tracker.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserImp implements User{

    UserRepository userRepository;
    TransactionRepository transactionRepository;
    PasswordEncoder passwordEncoder;
    @Autowired
    UserImp(UserRepository userRepository,TransactionRepository transactionRepository,PasswordEncoder passwordEncoder) {
        this.userRepository=userRepository;
        this.transactionRepository=transactionRepository;
        this.passwordEncoder=passwordEncoder;
    }

    private boolean isUserExists(String userName) {
        UserDetails user = userRepository.findByUserName(userName);
        return user!=null;
    }

    @Override
    @Transactional
    public int saveUser(UserDetails user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(isUserExists(user.getUserName())) {
            throw new IllegalArgumentException("User already exists with user-name " + user.getUserName());
        }
        UserDetails user1 = userRepository.save(user);
        return user1.getUserId();
    }

    @Override
    public UserDetails getUser(int userId) {
        UserDetails user = userRepository.findByUserId(userId);
        if(user==null) {
            throw new IllegalArgumentException("User details not found for the userId: " + userId);
        }
        return user;
    }

    @Override
    @Transactional
    public int modifyUserDetails(UserDetails user) {
        UserDetails user1 = userRepository.save(user);
        user1.setPassword(passwordEncoder.encode(user.getPassword()));
        return user1.getUserId();
    }

    @Override
    @Transactional
    public int deleteUser(int userId) {
        UserDetails user = getUser(userId);
        List<Transaction> transactionList = transactionRepository.getALlTransaction(userId);
        for(Transaction transaction : transactionList) {
            transactionRepository.delete(transaction);
        }
        userRepository.delete(user);
        return userId;
    }

}
