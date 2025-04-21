package com.expense_tracker.Expense.Tracker.rest;

import com.expense_tracker.Expense.Tracker.model.ResponseMessage;
import com.expense_tracker.Expense.Tracker.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OTPController {

    OTPService otpService;
    @Autowired
    OTPController(OTPService otpService){
        this.otpService=otpService;
    }

    @GetMapping("otp/generate/{phoneNumber}")
    public ResponseEntity<ResponseMessage> generateOtp(@PathVariable String phoneNumber) {
        String otp = otpService.generateOTP(phoneNumber);
        if(otp!="-1")
            otpService.saveOTP(phoneNumber, otp);
        otpService.sendOTP(phoneNumber, otp);
        return ResponseEntity.ok(new ResponseMessage("otp generated"));
    }


    @GetMapping("otp/verify/{phoneNumber}/{otp}")
    public ResponseEntity<ResponseMessage> verifyOtp(@PathVariable String phoneNumber,@PathVariable String otp) {
        if(!otpService.verifyOTP(phoneNumber, otp)){
            return ResponseEntity.ok(new ResponseMessage("otp verification failed"));
        }
        return ResponseEntity.ok(new ResponseMessage("otp verified"));
    }


}
