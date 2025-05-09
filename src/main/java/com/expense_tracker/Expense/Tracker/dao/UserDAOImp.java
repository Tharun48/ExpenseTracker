package com.expense_tracker.Expense.Tracker.dao;

import com.expense_tracker.Expense.Tracker.exceptionhandler.BadRequestException;
import com.expense_tracker.Expense.Tracker.model.Authorities;
import com.expense_tracker.Expense.Tracker.model.UserDetails;
import com.expense_tracker.Expense.Tracker.repository.UserRepository;
import com.expense_tracker.Expense.Tracker.service.User;
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
    public int saveUserDAO(UserDetails user) {

        UserDetails userExsists= userRepository.findByUserName(user.getUserName());
        if(userExsists!=null){
            throw new BadRequestException("User already exsits with user-name " + user.getUserName());
        }
        UserDetails userDetails= entityManager.merge(user);
        /*
        Authorities authorities = new Authorities();
        authorities.setAuthority("READ");
        authorities.addUser(userDetails);
        Authorities authorities1 = entityManager.merge(authorities);
        userDetails.addAuthorities(authorities1);
         */
        return userDetails.getUserId();
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
    public int modifyUserDetailsDAO(UserDetails user) {
        entityManager.merge(user);
        return user.getUserId();
    }

    @Override
    public int deleteUserDAO(int userId) {
        UserDetails user = getUserDAO(userId);
        entityManager.remove(user);
        return userId;
    }
}
