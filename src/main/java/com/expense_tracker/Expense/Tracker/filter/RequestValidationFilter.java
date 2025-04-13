package com.expense_tracker.Expense.Tracker.filter;

import com.expense_tracker.Expense.Tracker.exceptionhandler.BadRequestException;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class RequestValidationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        String header = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);
        if(header!=null){
            if(header.startsWith("Basic ")){
                header = header.substring(6);
                byte[] base64=header.getBytes(StandardCharsets.UTF_8);
                byte[] decode;
                try{
                    decode= Base64.getDecoder().decode(base64);
                    String userPass= new String(decode,StandardCharsets.UTF_8);
                    int index=userPass.indexOf(":");
                    if(index==-1) {
                        throw new com.expense_tracker.Expense.Tracker.exceptionhandler.BadRequestException("Invalid basic authentication token ");
                    }
                    String userName=userPass.substring(0,index);
                    if(userName.contains("test")) {
                        httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        httpServletResponse.setContentType("application/json");
                        httpServletResponse.getWriter().write("{\"error\": \"Invalid basic authentication token\"}");
                        return;
                    }
                }
                catch (RuntimeException e) {
                    throw new BadRequestException("Failed to decode username and password");
                }
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
