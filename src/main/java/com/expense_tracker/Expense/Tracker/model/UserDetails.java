package com.expense_tracker.Expense.Tracker.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="user_details")
public class UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int userId;

    @Column(name="user_name",unique = true)
    private String userName;

    @Column(name="password")
    private String password;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    public UserDetails() {}

    public UserDetails(String userName, String password, String firstName, String lastName) {
        this.userName = userName;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Authorities> getAuthoritiesList() {
        return authoritiesList;
    }

    public void setAuthoritiesList(List<Authorities> authoritiesList) {
        this.authoritiesList = authoritiesList;
    }

    public void addTransaction(Transaction transaction) {
        if( transactionList == null || transactionList.isEmpty()) {
            transactionList = new ArrayList<>();
        }
        transactionList.add(transaction);
    }

    public void addCategory(Category category){
        if(categoryList.isEmpty()) {
            categoryList = new ArrayList<>();
        }
        categoryList.add(category);
    }

    public void addAuthorities(Authorities authorities) {
        if(authoritiesList==null) {
            authoritiesList = new ArrayList<>();
        }
        authoritiesList.add(authorities);
    }


    @OneToMany(mappedBy = "user_transaction_id",fetch = FetchType.LAZY)
    private List<Transaction> transactionList;

    @OneToMany(mappedBy = "user_category")
    private List<Category> categoryList;

    @OneToMany(mappedBy = "user_authority")
    private List<Authorities> authoritiesList;

}
