package com.expense_tracker.Expense.Tracker.securityconfig;

import com.expense_tracker.Expense.Tracker.model.Authorities;
import com.expense_tracker.Expense.Tracker.repository.AuthorityRepository;
import com.expense_tracker.Expense.Tracker.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ExpenseTrackerUserDetailsService implements UserDetailsService {

    UserRepository userRepository;
    AuthorityRepository authorityRepository;

    ExpenseTrackerUserDetailsService(UserRepository userRepository,AuthorityRepository authorityRepository) {
        this.userRepository=userRepository;
        this.authorityRepository=authorityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.expense_tracker.Expense.Tracker.model.UserDetails userDetails = userRepository.findByUserName(username);
        if(userDetails==null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        ArrayList<Authorities> authoritiesList = authorityRepository.findByUserId(userDetails.getUserId());
        ArrayList<GrantedAuthority> authorityList = new ArrayList<>();
        for(Authorities authority : authoritiesList) {
            authorityList.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }
        return new User(userDetails.getUserName(),userDetails.getPassword(),authorityList);
    }
}
