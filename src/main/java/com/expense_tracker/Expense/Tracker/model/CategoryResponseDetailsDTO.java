package com.expense_tracker.Expense.Tracker.model;

public record CategoryResponseDetailsDTO(
        int categoryId,
        int userId,
        String categoryName,
        int categoryType
) {
}
