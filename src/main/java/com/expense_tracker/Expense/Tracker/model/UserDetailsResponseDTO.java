package com.expense_tracker.Expense.Tracker.model;

public record UserDetailsResponseDTO(
        int userId,
        String userName,
        String firstName,
        String lastName
) {
}
