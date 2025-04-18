package com.expense_tracker.Expense.Tracker.service;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OTPServiceImp implements OTPService{


    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String fromNumber;

    private final Map<String, String> otpStore = new HashMap<>();

    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public String generateOTP(String phoneNumber) {
        int otp = 1000000 + RANDOM.nextInt(9000000);
        return String.valueOf(otp);
    }

    public void sendOTP(String toPhoneNumber, String otp) {
        Message.creator(
                new PhoneNumber(toPhoneNumber),
                new PhoneNumber(fromNumber),
                "Your otp is  " + otp
        ).create();
    }


    public void saveOTP(String phoneNumber, String otp) {
        otpStore.put(phoneNumber, otp);
    }

    public boolean verifyOTP(String phoneNumber, String enteredOtp) {
        boolean flag = enteredOtp.equals(otpStore.get(phoneNumber));
        if(flag) {
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(fromNumber),
                    "You have logged in successfully into Expense Tracker "
            ).create();
        }
        else{
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(fromNumber),
                    "Login in failed"
            ).create();
        }
        return flag;
    }


}
