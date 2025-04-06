package com.expense_tracker.Expense.Tracker.repository;

import com.expense_tracker.Expense.Tracker.model.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserDetails,Integer> {
    UserDetails findByUserId(int userId);
}
