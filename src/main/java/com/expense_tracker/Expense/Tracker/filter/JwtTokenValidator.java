package com.expense_tracker.Expense.Tracker.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String path = request.getServletPath();
        if (path.startsWith("/swagger-ui") || path.startsWith("/v3/api-docs") || path.startsWith("/favicon.ico")) {
            filterChain.doFilter(request, response);
            return;
        }
        try{
            String key = "expenseTrackerKey-JWT-HMAC-SHA-algorithm requries 256 bit key";
            SecretKey secretKey = Keys.hmacShaKeyFor(key.getBytes(StandardCharsets.UTF_8));
            Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(header).getPayload();
            String username = claims.get("username").toString();
            String roles = claims.get("roles").toString();
            List<GrantedAuthority> authorities = new ArrayList<>();
            String[] rolesArray = roles.split(",");
            for(String role : rolesArray) {
                authorities.add(new SimpleGrantedAuthority(role));
            }
            Authentication authentication=new UsernamePasswordAuthenticationToken(username,"",authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch (ExpiredJwtException e) {
            throw new BadCredentialsException(e.getMessage());
        }
        filterChain.doFilter(request,response);
    }
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return request.getServletPath().equals("/user/login") || request.getServletPath().equals("/user") || request.getServletPath().startsWith("/otp/");
    }
}
