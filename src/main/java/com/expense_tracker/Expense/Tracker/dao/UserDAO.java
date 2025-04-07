package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Transaction;
import com.expense_tracker.Expense.Tracker.model.UserDetails;

public interface UserDAO {
    int saveUserDAO(UserDetails user);
    UserDetails getUserDAO(int userId);
    int modifyUserDetailsDAO(UserDetails user);
    int deleteUserDAO(int userId);
}
