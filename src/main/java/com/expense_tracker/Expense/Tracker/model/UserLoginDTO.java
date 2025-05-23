package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="user-login", description = "User login details")
public record UserLoginDTO(

        @Schema(description = "User Name of the user",example ="Tharun" )
        String userName,

        @Schema(description = "Password of the user",example ="Tharun@123" )
        String password,

        @Schema(description = "first-name of the user",example ="Tharun" )
        String firstName,

        @Schema(description = "last-name of user to get user",example = "Kumar")
        String lastName
) {
}
