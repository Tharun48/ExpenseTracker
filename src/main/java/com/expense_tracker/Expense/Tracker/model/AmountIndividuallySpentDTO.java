package com.expense_tracker.Expense.Tracker.model;

import java.time.LocalDate;

public record AmountIndividuallySpentDTO(
        String item,
        double amount,
        LocalDate transactionDate
) {
}
