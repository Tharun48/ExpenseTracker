package com.expense_tracker.Expense.Tracker.model;


import java.util.Date;

public record OTPExpirationDetails(
        String otp,
        Date otpExpiryDetails,
        int countOfFailureAttempts,
        Date lastLogin,
        Date accountLockedTill
) {
}
