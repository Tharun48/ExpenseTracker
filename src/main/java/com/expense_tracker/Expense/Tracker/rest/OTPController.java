package com.expense_tracker.Expense.Tracker.rest;

import com.expense_tracker.Expense.Tracker.model.OtpVerificationDTO;
import com.expense_tracker.Expense.Tracker.model.ResponseMessage;
import com.expense_tracker.Expense.Tracker.service.OTPService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name="one-time-password")
public class OTPController {

    OTPService otpService;
    @Autowired
    OTPController(OTPService otpService){
        this.otpService=otpService;
    }

    @GetMapping("otp/generate/{phoneNumber}")
    @Operation(
            summary = "Generate OTP",
            description = "This api is used to generate one-time-password"
    )
    public ResponseEntity<ResponseMessage> generateOtp(@PathVariable String phoneNumber) {
        String otp = otpService.generateOTP(phoneNumber);
        if(otp!="-1")
            otpService.saveOTP(phoneNumber, otp);
        otpService.sendOTP(phoneNumber, otp);
        if(otp!="-1")
            return ResponseEntity.ok(new ResponseMessage("otp generated"));
        return ResponseEntity.ok(new ResponseMessage("otp failed to generated"));
    }


    @PostMapping("otp/verify")
    @Operation(
            summary = "Verify OTP",
            description = "This api is verify the generated one-time-password"
    )
    public ResponseEntity<ResponseMessage> verifyOtp(@RequestBody OtpVerificationDTO otpVerificationDTO) {
        String phoneNumber=otpVerificationDTO.phoneNumber();
        String otp=otpVerificationDTO.otp();
        if(!otpService.verifyOTP(phoneNumber, otp)){
            return ResponseEntity.ok(new ResponseMessage("otp verification failed"));
        }
        return ResponseEntity.ok(new ResponseMessage("otp verified"));
    }


}
