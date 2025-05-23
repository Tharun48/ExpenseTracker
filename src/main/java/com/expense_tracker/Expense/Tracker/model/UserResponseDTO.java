package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="user")
public record UserResponseDTO(
        @Schema(description = "Id of user",example ="1" )
        int userId
) {
}
