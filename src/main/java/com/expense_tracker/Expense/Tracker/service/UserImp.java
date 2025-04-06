package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.dao.UserDAO;
import com.expense_tracker.Expense.Tracker.model.UserDetails;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImp implements User{

    UserDAO userDAO;
    @Autowired
    UserImp(UserDAO userDAO){
        this.userDAO=userDAO;
    }


    @Override
    @Transactional
    public void saveUser(UserDetails user) {
        userDAO.saveUserDAO(user);
    }

    @Override
    public UserDetails getUser(int userId) {
        return userDAO.getUserDAO(userId);
    }

    @Override
    public void modifyUserDetails(UserDetails user) {
        userDAO.modifyUserDetailsDAO(user);
    }

    @Override
    public void deleteUser(int userId) {
        userDAO.deleteUserDAO(userId);
    }
}
