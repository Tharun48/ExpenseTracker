package com.expense_tracker.Expense.Tracker.model;

public record UserRequestDTO(
        String userName,
        String password,
        String firstName,
        String lastName
) {

}
