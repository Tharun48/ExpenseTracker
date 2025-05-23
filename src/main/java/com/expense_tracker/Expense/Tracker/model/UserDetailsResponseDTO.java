package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="user-detail")
public record UserDetailsResponseDTO(

        @Schema(description = "Id of user",example ="1" )
        int userId,

        @Schema(description = "User Name of the user",example ="Tharun" )
        String userName,

        @Schema(description = "first-name of the user",example ="Tharun" )
        String firstName,

        @Schema(description = "last-name of user to get user",example = "Kumar")
        String lastName
) {
}
