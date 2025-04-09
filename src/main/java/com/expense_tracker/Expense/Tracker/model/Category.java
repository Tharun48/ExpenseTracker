package com.expense_tracker.Expense.Tracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="category")
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="category_id")
    private int categoryId;

    @Column(name="category_name")
    private String categoryName;

    @Column(name="category_type")
    private int categoryType;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserDetails user_category;

    public void addUser(UserDetails userDetails){
        this.user_category=userDetails;
        userDetails.addCategory(this);
    }

    Category(){}

    public Category(String categoryName, int categoryType, UserDetails user_category) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
        this.user_category = user_category;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(int categoryType) {
        this.categoryType = categoryType;
    }

    public UserDetails getUser_category() {
        return user_category;
    }

    public void setUser_category(UserDetails user_category) {
        this.user_category = user_category;
    }
}
