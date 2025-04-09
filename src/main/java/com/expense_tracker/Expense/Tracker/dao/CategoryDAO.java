package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Category;

public interface CategoryDAO {
    int save(Category category);
    Category getCategory(int categoryId);
    int modifyCategory(Category category);
    int deleteCategory(int categoryId);
}
