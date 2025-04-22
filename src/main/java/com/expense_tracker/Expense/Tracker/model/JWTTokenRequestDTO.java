package com.expense_tracker.Expense.Tracker.model;

public record JWTTokenRequestDTO(
        String userName,
        String password
) {
}
