package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Category;

public interface CategoryDAO {
    void save(Category category);
    Category getCategory(int categoryId);
    void modifyCategory(Category category);
    void deleteCategory(int categoryId);
}
