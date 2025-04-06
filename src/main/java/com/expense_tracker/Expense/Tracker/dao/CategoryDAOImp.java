package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.Category;
import com.expense_tracker.Expense.Tracker.repository.CategoryRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryDAOImp implements CategoryDAO{

    EntityManager entityManager;
    CategoryRepository categoryRepository;

    @Autowired
    CategoryDAOImp(EntityManager entityManager,CategoryRepository categoryRepository){
        this.entityManager=entityManager;
        this.categoryRepository=categoryRepository;
    }

    @Override
    public void save(Category category) {
        entityManager.persist(category);
    }

    @Override
    public Category getCategory(int categoryId) {
        Category category = categoryRepository.findByCategoryId(categoryId);
        if(category==null) {
            throw new IllegalArgumentException("Category details not found for the categoryId: " + categoryId);
        }
        return category;
    }

    @Override
    public void modifyCategory(Category category) {
        entityManager.merge(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        Category category = getCategory(categoryId);
        entityManager.remove(category);
    }
}
