package com.expense_tracker.Expense.Tracker.model;

public record TransactionRequestDTO(
        int transactionId,
        Double transactionAmount,
        String description,
        int transactionCategory,
        int userId
) {
}
