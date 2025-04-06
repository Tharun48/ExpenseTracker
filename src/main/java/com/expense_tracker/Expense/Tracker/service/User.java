package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.UserDetails;

public interface User {
    void saveUser(UserDetails user);
    UserDetails getUser(int userId);
    void modifyUserDetails(UserDetails user);
    void deleteUser(int userId);
}
