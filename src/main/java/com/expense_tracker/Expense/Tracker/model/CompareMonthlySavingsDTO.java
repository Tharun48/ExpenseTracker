package com.expense_tracker.Expense.Tracker.model;

import java.util.Map;

public record CompareMonthlySavingsDTO(
        Map<String,double[]> comparingMonthlySaving
) {
}
