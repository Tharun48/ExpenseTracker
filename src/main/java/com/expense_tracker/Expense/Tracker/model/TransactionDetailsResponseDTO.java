package com.expense_tracker.Expense.Tracker.model;

import java.time.LocalDate;

public record TransactionDetailsResponseDTO(
        int transactionId,
        int userId,
        Double transactionAmount,
        String description,
        int transactionCategory,
        LocalDate date
) {

}
