package com.expense_tracker.Expense.Tracker.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
    private int transactionId;

    @Column(name="date")
    private Date createdOn;

    @Column(name="transaction_category")
    private String transactionCategory;

    @Column(name="description")
    private String description;

    @Column(name="transaction_amount")
    private double transactionAmount;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDetails user_transaction_id;


}
