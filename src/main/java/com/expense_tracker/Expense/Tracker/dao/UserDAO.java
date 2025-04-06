package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.model.UserDetails;

public interface UserDAO {
    void saveUserDAO(UserDetails user);
    UserDetails getUserDAO(int userId);
    void modifyUserDetailsDAO(UserDetails user);
    void deleteUserDAO(int userId);
}
