package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.dao.CategoryDAO;
import com.expense_tracker.Expense.Tracker.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryImp implements CategoryDetails {

    CategoryDAO categoryDAO;
    CategoryImp(CategoryDAO categoryDAO) {
        this.categoryDAO=categoryDAO;
    }

    @Override
    @Transactional
    public void saveCategoryDAO(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public Category getCategoryDAO(int categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    @Override
    @Transactional
    public void modifyCategoryDAO(Category category) {
        categoryDAO.modifyCategory(category);
    }

    @Override
    public void deleteCategoryDAO(int categoryId) {
        categoryDAO.deleteCategory(categoryId);
    }
}
