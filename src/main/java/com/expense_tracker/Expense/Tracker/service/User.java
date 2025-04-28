package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.CompareMonthlySavingsDTO;
import com.expense_tracker.Expense.Tracker.model.UserDetails;

import java.time.LocalDate;

public interface User {
    int saveUser(UserDetails user);
    UserDetails getUser(int userId);
    int modifyUserDetails(UserDetails user);
    int deleteUser(int userId);
    CompareMonthlySavingsDTO comparingMonthlySavingsDTO(int userId, int firstYear, int firstMonth, int secondYear, int secondMonth);
}
