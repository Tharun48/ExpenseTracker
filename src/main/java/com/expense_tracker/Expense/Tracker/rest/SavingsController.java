package com.expense_tracker.Expense.Tracker.rest;

import com.expense_tracker.Expense.Tracker.model.*;
import com.expense_tracker.Expense.Tracker.service.TransactionDetails;
import com.expense_tracker.Expense.Tracker.service.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name="savings")
public class SavingsController {

    TransactionDetails transactionDetails;
    User user;
    TransactionController transactionController;
    private final ModelMapper modelMapper;
    SavingsController(ModelMapper modelMapper,TransactionDetails transactionDetails,
                      User user,TransactionController transactionController) {
        this.transactionDetails = transactionDetails;
        this.user=user;
        this.modelMapper = modelMapper;
        this.transactionController=transactionController;
    }

    @GetMapping("/user/{userId}/savings")
    @Operation(
            summary = "Get Amount spent Individually",
            description = "This api is get amount spent individually",
            security = @SecurityRequirement(name = "BearerAuthentication")
    )
    public ResponseEntity<SavingsResponseDTO> getSavings(
            @PathVariable int userId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate) {
        SavingsResponseDTO savingsResponseDTO = transactionDetails.savings(userId,fromDate,toDate);
        return ResponseEntity.ok(savingsResponseDTO);
    }


    @GetMapping("/users/{userId}/savings/compare")
    @Operation(
            summary = "Compare monthly savings",
            description = "This api is used to compare monthly savings by providing the start and end dates of both the months",
            security = @SecurityRequirement(name = "BearerAuthentication")
    )
    public ResponseEntity<CompareMonthlySavingsDTO> compareMonthlySavings(
            @PathVariable int userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstMonthStart, // Format: YYYY-MM
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate firstMonthEnd,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate secondMonthStart, // Format: YYYY-MM
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate secondMonthEnd
    ) {
        CompareMonthlySavingsDTO compareMonthlySavingsDTO = user.comparingMonthlySavingsDTO(userId,firstMonthStart,firstMonthEnd,secondMonthStart,secondMonthEnd);
        return ResponseEntity.ok(compareMonthlySavingsDTO);
    }

    @GetMapping("/user/{userId}/spent-over/{amount}")
    @Operation(
            summary = "Get Amount spent over a particular limit",
            description = "This api is to get items spent over a particular limit",
            security = @SecurityRequirement(name = "BearerAuthentication")
    )
    public ResponseEntity<List<AmountIndividuallySpentDTO>> getSavings(
            @PathVariable int userId,
            @PathVariable double amount,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
    )
    {

        List<Transaction> transactionList = transactionDetails.getTransactionWithDate(userId,fromDate,toDate);
        Map<String,Double> map = new HashMap<>();
        for(Transaction transaction:transactionList){
            String description = transaction.getDescription();
            map.put(description,map.getOrDefault(description,0.0)+transaction.getTransactionAmount());
        }
        List<AmountIndividuallySpentDTO> list = new ArrayList<>();
        for(Map.Entry<String,Double> entry:map.entrySet()) {
            if(entry.getValue()>amount){
                list.add(new AmountIndividuallySpentDTO(entry.getKey(),entry.getValue()));
            }
        }
        return ResponseEntity.ok(list);
    }
}
