package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="transaction-response")
public record TransactionResponseDTO(
    @Schema(description = "Id of transaction",example ="1" )
        int transactionId
) {
}
