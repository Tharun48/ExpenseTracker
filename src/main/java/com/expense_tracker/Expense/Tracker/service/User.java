package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.UserDetails;

public interface User {
    int saveUser(UserDetails user);
    UserDetails getUser(int userId);
    int modifyUserDetails(UserDetails user);
    int deleteUser(int userId);
}
