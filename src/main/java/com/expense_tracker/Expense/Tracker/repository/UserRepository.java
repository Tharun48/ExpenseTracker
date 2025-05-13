package com.expense_tracker.Expense.Tracker.repository;

import com.expense_tracker.Expense.Tracker.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDetails,Integer> {
    UserDetails findByUserId(int userId);
    UserDetails findByUserName(String userName);
}
