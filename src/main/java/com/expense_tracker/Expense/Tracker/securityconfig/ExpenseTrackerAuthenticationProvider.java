package com.expense_tracker.Expense.Tracker.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class ExpenseTrackerAuthenticationProvider implements AuthenticationProvider {

    ExpenseTrackerUserDetailsService expenseTrackerUserDetailsService;
    PasswordEncoder passwordEncoder;

    @Autowired
    ExpenseTrackerAuthenticationProvider(ExpenseTrackerUserDetailsService expenseTrackerUserDetailsService,PasswordEncoder passwordEncoder){
        this.expenseTrackerUserDetailsService=expenseTrackerUserDetailsService;
        this.passwordEncoder=passwordEncoder;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserDetails userDetails = expenseTrackerUserDetailsService.loadUserByUsername(username);
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid password");
        }
        return new UsernamePasswordAuthenticationToken(username, password, userDetails.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
