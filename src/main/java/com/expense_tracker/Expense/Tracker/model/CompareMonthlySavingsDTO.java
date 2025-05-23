package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;
@Schema(name="compare-monthly-savings")
public record CompareMonthlySavingsDTO(
        @Schema(description = "map that displays the savings of each month",example = "{food={[100.0,200.0]},rent={[100.0,200.0]}}")
        Map<String,double[]> comparingMonthlySaving
) {
}
