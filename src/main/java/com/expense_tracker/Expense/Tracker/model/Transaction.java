package com.expense_tracker.Expense.Tracker.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int transactionId;

    @Column(name="transaction_date")
    private LocalDate createdOn;

    @Column(name="transaction_category")
    private int transactionCategory;

    @Column(name="description")
    private String description;

    @Column(name="transaction_amount",nullable = false)
    @Min(value = 0, message = "transaction amount cannot be negative")
    private double transactionAmount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDetails user_transaction_id;

    public void addUser(UserDetails userDetails) {
        this.user_transaction_id=userDetails;
        userDetails.addTransaction(this);
    }

    public Transaction() {}

    public Transaction(int transactionCategory, String description, double transactionAmount, UserDetails user_transaction_id, LocalDate createdOn) {
        this.transactionCategory = transactionCategory;
        this.description = description;
        this.transactionAmount = transactionAmount;
        this.user_transaction_id = user_transaction_id;
        this.createdOn=createdOn;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public int getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(int transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public UserDetails getUser_transaction_id() {
        return user_transaction_id;
    }

    public void setUser_transaction_id(UserDetails user_transaction_id) {
        this.user_transaction_id = user_transaction_id;
    }
}
