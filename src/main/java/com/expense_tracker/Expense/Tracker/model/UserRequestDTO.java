package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name="modify-user",
        description="User details"
)
public record UserRequestDTO(
        @Schema(description = "Id of user",example ="1" )
        String userId,

        @Schema(description = "user Name of the user",example ="Tharun" )
        String userName,

        @Schema(description = "Password of the user",example ="Tharun@123" )
        String password,

        @Schema(description = "first-name of the user",example ="Tharun" )
        String firstName,

        @Schema(description = "last-name of user to get user",example = "Kumar")
        String lastName
) {

}
