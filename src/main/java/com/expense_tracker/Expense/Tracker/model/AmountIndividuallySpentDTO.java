package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
@Schema(name="amount-spent-individually")
public record AmountIndividuallySpentDTO(

        @Schema(description = "item of the transaction",example ="Groceries" )
        String item,

        @Schema(description = "amount of the transaction",example ="1000.00" )
        double amount,

        @Schema(description = "date of the transaction",example ="2023-01-01" )
        LocalDate transactionDate
) {
}
