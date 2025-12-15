package com.expense_tracker.Expense.Tracker.securityconfig;

import com.expense_tracker.Expense.Tracker.filter.JwtTokenValidator;
import com.expense_tracker.Expense.Tracker.filter.RequestValidationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .addFilterBefore(new RequestValidationFilter(),BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request->request
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/user/register").permitAll()
                .requestMatchers(HttpMethod.POST,"/user/login").permitAll()
                .requestMatchers(HttpMethod.GET,"/transaction/user/**").permitAll()
                .requestMatchers("/error","/swagger-ui/**","/v3/api-docs/**","/swagger-ui/index.html","/favicon.ico","/swagger-ui.html").permitAll()
                .requestMatchers("/otp/**").permitAll()
                .anyRequest().authenticated()
        );

        http.logout(logout-> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/swagger-ui/index.html")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID").permitAll()
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
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowedOrigins(List.of(
                "http://127.0.0.1:5500",
                "http://localhost:5500"
        ));

        config.setAllowedMethods(List.of(
                "GET","POST","PUT","DELETE","OPTIONS"
        ));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }


    @Bean
    public AuthenticationManager authenticationManager(ExpenseTrackerUserDetailsService expenseTrackerUserDetailsService,
                                                       PasswordEncoder passwordEncoder) {
        ExpenseTrackerAuthenticationProvider authenticationProvider =
                new ExpenseTrackerAuthenticationProvider(expenseTrackerUserDetailsService,passwordEncoder);
        ProviderManager providerManager = new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker(){
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
