package com.expense_tracker.Expense.Tracker.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name="otp-verification")
public record OtpVerificationDTO(
        @Schema(description = "phone number of the user",example ="+91 9632587410" )
        String phoneNumber,

        @Schema(description = "six digits otp",example ="452136" )
        String otp
) {
}
