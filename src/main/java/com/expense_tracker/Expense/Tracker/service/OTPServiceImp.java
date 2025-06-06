package com.expense_tracker.Expense.Tracker.service;
import com.expense_tracker.Expense.Tracker.model.OTPExpirationDetails;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Date;
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

    private final Map<String, OTPExpirationDetails> otpDetails = new HashMap<>();


    @PostConstruct
    public void init() {
        Twilio.init(accountSid, authToken);
    }

    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public String generateOTP(String phoneNumber) {
        if(otpDetails.containsKey(phoneNumber)){
            OTPExpirationDetails otpExpirationDetails =otpDetails.get(phoneNumber);
            if(otpExpirationDetails.countOfFailureAttempts()>=3 || (otpExpirationDetails.accountLockedTill()!=null && otpExpirationDetails.accountLockedTill().after(new Date())) ){
                return "-1";
            }
        }
        int otp = 1000000 + RANDOM.nextInt(6000000);
        return String.valueOf(otp);
    }

    public void sendOTP(String toPhoneNumber, String otp) {
        if(otp.equals("-1")) {
            OTPExpirationDetails otpExpirationDetails=otpDetails.get(toPhoneNumber);
//            OTPExpirationDetails otpExpirationDetails1=new OTPExpirationDetails("",null,0,null,new Date(new Date().getTime()+60000));
            long diffInMillis = otpDetails.get(toPhoneNumber).accountLockedTill().getTime()-new Date().getTime() ;
            long diffInMinutes = (long) Math.ceil(diffInMillis / (60.0 * 1000));
//            otpDetails.put(toPhoneNumber,otpExpirationDetails1);
            Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(fromNumber),
                    "Too many failed attempts. Try again after " + diffInMinutes + " minutes"
            ).create();
        }
        else{
            Message.creator(
                    new PhoneNumber(toPhoneNumber),
                    new PhoneNumber(fromNumber),
                    "Your otp is  " + otp
            ).create();
        }
    }


    public void saveOTP(String phoneNumber, String otp) {
        if(otpDetails.containsKey(phoneNumber)){
            OTPExpirationDetails otpExpirationDetails=new OTPExpirationDetails(otp, new Date(new Date().getTime() + 60000),otpDetails.get(phoneNumber).countOfFailureAttempts(),null,null);
            otpDetails.put(phoneNumber,otpExpirationDetails);
        }
        else{
            OTPExpirationDetails otpExpirationDetails = new OTPExpirationDetails(otp, new Date(new Date().getTime() + 60000),0,null,null);
            otpDetails.put(phoneNumber,otpExpirationDetails);
        }
        return;
    }



    public boolean verifyOTP(String phoneNumber, String enteredOtp) {
        boolean flag=false;
        if(otpDetails.containsKey(phoneNumber)) {
            OTPExpirationDetails otpExpirationDetails = otpDetails.get(phoneNumber);
            String storedOTP = otpExpirationDetails.otp();
            Date otpStoredTime = otpExpirationDetails.otpExpiryDetails();
            Date currentTime = new Date();
            if (storedOTP.equals(enteredOtp) && currentTime.before(otpStoredTime)) {
                flag = true;
            }
        }
        if (flag) {
            OTPExpirationDetails otpExpirationDetails = otpDetails.get(phoneNumber);
            OTPExpirationDetails otpExpirationDetails1 = new OTPExpirationDetails("",null,0,new Date(),null);
            otpDetails.put(phoneNumber,otpExpirationDetails1);
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(fromNumber),
                    "You have logged in successfully into Expense Tracker "
            ).create();
        } else {
            OTPExpirationDetails otpExpirationDetails = otpDetails.get(phoneNumber);
            Date accountLockedTill=null;
            if(otpExpirationDetails.countOfFailureAttempts()>=2) {
                accountLockedTill=new Date(new Date().getTime()+600000);
            }
            OTPExpirationDetails otpExpirationDetails1 = new OTPExpirationDetails("",null,otpExpirationDetails.countOfFailureAttempts()+1,null,accountLockedTill);
            otpDetails.put(phoneNumber,otpExpirationDetails1);
            Message.creator(
                    new PhoneNumber(phoneNumber),
                    new PhoneNumber(fromNumber),
                    "Login in failed"
            ).create();
        }
        return flag;
    }

}
