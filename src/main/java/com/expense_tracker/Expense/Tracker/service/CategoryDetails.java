package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.Category;

public interface CategoryDetails {
    int saveCategoryDAO(Category category);
    Category getCategoryDAO(int categoryId);
    int modifyCategoryDAO(Category category);
    int deleteCategoryDAO(int categoryId);

}
