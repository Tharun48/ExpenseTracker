package com.expense_tracker.Expense.Tracker.model;

import java.time.LocalDate;

public record TransactionRequestDTO(
        int transactionId,
        Double transactionAmount,
        String description,
        LocalDate createdOn,
        int transactionCategory,
        int userId
) {
}
