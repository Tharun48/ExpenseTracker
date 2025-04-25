package com.expense_tracker.Expense.Tracker.rest;

import com.expense_tracker.Expense.Tracker.model.*;
import com.expense_tracker.Expense.Tracker.service.TransactionDetails;
import com.expense_tracker.Expense.Tracker.service.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Tag(name="transaction")
public class TransactionController {

    TransactionDetails transactionDetails;
    User user;
    private final ModelMapper modelMapper;

    @Autowired
    public TransactionController(TransactionDetails transactionDetails,ModelMapper modelMapper,User user) {
        this.transactionDetails = transactionDetails;
        this.modelMapper=modelMapper;
        this.user=user;
    }

    @PostMapping("/transaction")
    @Operation(
            summary = "Add a new transaction",
            description = "add new transaction based on category",
            security = @SecurityRequirement(name = "BearerAuthentication")
    )
    public ResponseEntity<TransactionResponseDTO> saveTransaction(@RequestBody TransactionAddRequestDTO transactionRequestDTO) {
        UserDetails userDetails = user.getUser(transactionRequestDTO.userId());
        Transaction transaction = new Transaction(transactionRequestDTO.transactionCategory(),transactionRequestDTO.description(),transactionRequestDTO.transactionAmount(),userDetails,transactionRequestDTO.createdOn());
        transaction.addUser(userDetails);
        int transactionId = transactionDetails.saveTransaction(transaction);
        return ResponseEntity.ok(new TransactionResponseDTO(transactionId));
    }

    @PutMapping("/transaction")
    @Operation(
            summary = "Modify Existing transaction",
            description = "This api is used to add a modify transaction",
            security = @SecurityRequirement(name = "BearerAuthentication")
    )
    public ResponseEntity<TransactionResponseDTO> modifyTransaction(@RequestBody TransactionRequestDTO transactionRequestDTO) {
        UserDetails userDetails = user.getUser(transactionRequestDTO.userId());
        Transaction transaction = new Transaction(transactionRequestDTO.transactionCategory(),transactionRequestDTO.description(),transactionRequestDTO.transactionAmount(),userDetails,transactionRequestDTO.createdOn());
        transaction.setUser_transaction_id(userDetails);
        transaction.setTransactionId(transactionRequestDTO.transactionId());
        int transactionId = transactionDetails.modifyTransactionDetails(transaction);
        return ResponseEntity.ok(new TransactionResponseDTO(transactionId));
    }


    @DeleteMapping("/transaction/{transactionId}")
    @Operation(
            summary = "Delete transaction",
            description = "This api is used to delete a transaction",
            security = @SecurityRequirement(name = "BearerAuthentication")
    )
    public ResponseEntity<TransactionResponseDTO> deleteTransaction(@PathVariable int transactionId) {
        transactionDetails.deleteTransaction(transactionId);
        return ResponseEntity.ok(new TransactionResponseDTO(transactionId));
    }


    @GetMapping("/transaction/user/{userId}")
    @Operation(
            summary = "Get transaction details",
            description = "This api is get transaction details for a specific user",
            security = @SecurityRequirement(name = "BearerAuthentication")
    )
    public ResponseEntity<List<TransactionRequestDTO>> getTransaction(@PathVariable int userId) {
        List<Transaction> transactions = transactionDetails.getTransactionUser(userId);
        List<TransactionRequestDTO> list = new ArrayList<>();
        for(Transaction transaction:transactions){
            TransactionRequestDTO transactionRequestDTO =new TransactionRequestDTO(transaction.getTransactionId(),
                    transaction.getTransactionAmount(), transaction.getDescription(),transaction.getCreatedOn(),
                    transaction.getTransactionCategory(),transaction.getUser_transaction_id().getUserId());
            list.add(transactionRequestDTO);
        }
        return ResponseEntity.ok(list);
    }

}
