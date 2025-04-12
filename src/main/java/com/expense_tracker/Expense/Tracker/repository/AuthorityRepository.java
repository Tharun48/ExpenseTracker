package com.expense_tracker.Expense.Tracker.repository;

import com.expense_tracker.Expense.Tracker.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authorities,Integer> {
    @Query("SELECT a FROM Authorities a WHERE a.user_authority.userId = :userId")
    ArrayList<Authorities> findByUserId(@Param("userId") int userId);
}
