package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record TransactionAddRequestDTO(
        Double transactionAmount,
        String description,
        LocalDate createdOn,
        @Schema(description = "transaction category",allowableValues = {"Income = 1","Expense = 2"})
        int transactionCategory,
        int userId
) {
}
