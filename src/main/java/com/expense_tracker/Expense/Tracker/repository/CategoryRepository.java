package com.expense_tracker.Expense.Tracker.repository;

import com.expense_tracker.Expense.Tracker.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    Category findByCategoryId(int categoryId);
}
