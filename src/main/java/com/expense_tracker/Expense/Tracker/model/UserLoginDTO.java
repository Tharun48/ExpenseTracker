package com.expense_tracker.Expense.Tracker.model;

public record UserLoginDTO(
        String userName,
        String password,
        String firstName,
        String lastName
) {
}
