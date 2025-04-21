package com.expense_tracker.Expense.Tracker.filter;

import com.expense_tracker.Expense.Tracker.model.Authorities;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtTokenGenerator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String key = "expenseTrackerKey-JWT-HMAC-SHA-algorithm requries 256 bit key";
        SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        StringBuilder authorities = new StringBuilder("READ");
        for(GrantedAuthority authority : authentication.getAuthorities()) {
            authorities.append(",").append(authority.getAuthority());
        }
        String jwt = Jwts.builder()
                .issuer("Expense Tracker")
                .subject("jwt tokens")
                .claim("username", authentication.getName())
                .claim("roles", authorities.toString())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 6000000))
                .signWith(secretKey).compact();
        response.setHeader("Authorization", jwt);
        filterChain.doFilter(request,response);

    }
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/user/login");
    }
}
