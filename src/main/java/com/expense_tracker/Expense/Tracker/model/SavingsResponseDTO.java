package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
@Schema(name="savings")
public record SavingsResponseDTO(
        @Schema(description = "Total Savings",example = "1000.00")
        double savings,

        @Schema(description = "Amount spent Individually on",example = "{groceries:1000.00},{rent:800}")
        Map<String,Double> amountspentIndividuallyOn
) {
}
