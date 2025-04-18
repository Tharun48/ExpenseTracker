package com.expense_tracker.Expense.Tracker.service;

public interface OTPService {
    String generateOTP(String phoneNumber);
    void sendOTP(String toPhoneNumber, String otp);
    void saveOTP(String phoneNumber, String otp);
    boolean verifyOTP(String phoneNumber, String enteredOtp);
}
