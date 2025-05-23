package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
@Schema(name = "create-transaction")
public record TransactionAddRequestDTO(

        @Schema(description = "transaction amount of the user",example ="1000.00" )
        Double transactionAmount,

        @Schema(description = "transaction description of the user",example ="Groceries" )
        String description,

        @Schema(description = "transaction date of the user",example ="2023-01-01" )
        LocalDate createdOn,

        @Schema(description = "transaction category",allowableValues = {"Income = 1","Expense = 2"})
        int transactionCategory,

        @Schema(description = "user id of the user",example ="1" )
        int userId
) {
}
