package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="token")
public record JWTTokenDTO(
        @Schema(description = "token",example = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTYiLCJyb2xlIjoiYWRtaW4ifQ")
        String token
) {
}
