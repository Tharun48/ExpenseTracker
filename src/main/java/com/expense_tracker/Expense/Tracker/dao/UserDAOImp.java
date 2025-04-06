package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.model.UserDetails;
import com.expense_tracker.Expense.Tracker.repository.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImp implements UserDAO{
    UserRepository userRepository;
    EntityManager entityManager;
    @Autowired
    UserDAOImp(UserRepository userRepository,EntityManager entityManager){
        this.userRepository=userRepository;
        this.entityManager=entityManager;
    }

    @Override
    public void saveUserDAO(UserDetails user) {
        entityManager.persist(user);
    }

    @Override
    public UserDetails getUserDAO(int userId) {
        UserDetails userDetails = userRepository.findByUserId(userId);
        if(userDetails==null){
            throw new IllegalArgumentException("User details not found for the userId: " + userId);
        }
        return userDetails;
    }

    @Override
    public void modifyUserDetailsDAO(UserDetails user) {
        entityManager.merge(user);
    }

    @Override
    public void deleteUserDAO(int userId) {
        UserDetails user = getUserDAO(userId);
        entityManager.remove(user);
    }
}
