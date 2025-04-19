package com.expense_tracker.Expense.Tracker.securityconfig;

import com.expense_tracker.Expense.Tracker.filter.JwtTokenGenerator;
import com.expense_tracker.Expense.Tracker.filter.JwtTokenValidator;
import com.expense_tracker.Expense.Tracker.filter.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(csrf -> csrf.disable())
                .addFilterBefore(new RequestValidationFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JwtTokenGenerator(),BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidator(),BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request->request
                .requestMatchers(HttpMethod.POST,"/user").permitAll()
                .requestMatchers("/error","/swagger-ui/**","/v3/api-docs/**","/swagger-ui/index.html","/favicon.ico","/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.GET,"/otp/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/user/**").authenticated()
                .requestMatchers(HttpMethod.PUT,"/user/**").authenticated()
                .requestMatchers(HttpMethod.DELETE,"/user/**").authenticated()
                .requestMatchers(HttpMethod.GET,"/users/{userId}/savings/compare").authenticated()
                .requestMatchers(HttpMethod.GET,"/user/login/**").authenticated()
                .requestMatchers("/transaction/**","/category/**").authenticated()
        );
        http.formLogin(withDefaults()); //disable form login if your application is stateless
        http.httpBasic(withDefaults()); //disable http basic if your application primarily serves web pages
        return http.build();
    }
    /*
    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }
     */

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
