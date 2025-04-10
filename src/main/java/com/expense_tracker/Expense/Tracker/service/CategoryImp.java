package com.expense_tracker.Expense.Tracker.service;

import com.expense_tracker.Expense.Tracker.dao.CategoryDAO;
import com.expense_tracker.Expense.Tracker.exceptionhandler.BadRequestException;
import com.expense_tracker.Expense.Tracker.model.Category;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryImp implements CategoryDetails {

    CategoryDAO categoryDAO;
    CategoryImp(CategoryDAO categoryDAO) {
        this.categoryDAO=categoryDAO;
    }

    @Override
    @Transactional
    public int saveCategoryDAO(Category category) {
        if(!isValidCategoryType(category.getCategoryType())) {
            throw new BadRequestException("Invalid category type " + category.getCategoryType());
        }
        return categoryDAO.save(category);
    }

    @Override
    public Category getCategoryDAO(int categoryId) {
        return categoryDAO.getCategory(categoryId);
    }

    @Override
    @Transactional
    public int modifyCategoryDAO(Category category) {
        if(!isValidCategoryType(category.getCategoryType())) {
            throw new BadRequestException("Invalid category type " + category.getCategoryType());
        }
        return categoryDAO.modifyCategory(category);
    }

    @Override
    @Transactional
    public int deleteCategoryDAO(int categoryId) {
        return categoryDAO.deleteCategory(categoryId);
    }

    public boolean isValidCategoryType(int categoryType)   {
        return categoryType==1 || categoryType==2;
    }



}
