package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.model.Category;

public interface CategoryDetails {
    void saveCategoryDAO(Category category);
    Category getCategoryDAO(int categoryId);
    void modifyCategoryDAO(Category category);
    void deleteCategoryDAO(int categoryId);

}
