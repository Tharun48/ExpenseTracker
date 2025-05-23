package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "token")
public record JWTTokenRequestDTO(

        @Schema(description = "User Name of the user already registered",example ="Tharun" )
        String userName,

        @Schema(description = "Password of the user",example ="Tharun@123" )
        String password
) {
}
