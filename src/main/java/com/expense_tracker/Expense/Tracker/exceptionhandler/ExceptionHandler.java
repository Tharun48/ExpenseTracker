package com.expense_tracker.Expense.Tracker.exceptionhandler;

import com.expense_tracker.Expense.Tracker.model.ExceptionHandlerResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.boot.autoconfigure.batch.BatchTaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> exceptionHandler(IllegalArgumentException e) {
        ExceptionHandlerResponse exceptionHandlerResponse =  new ExceptionHandlerResponse();
        exceptionHandlerResponse.setStatus(HttpStatus.NOT_FOUND.value());
        exceptionHandlerResponse.setMessage(e.getMessage());
        exceptionHandlerResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> exceptionHandler(BadRequestException e) {
        ExceptionHandlerResponse exceptionHandlerResponse =  new ExceptionHandlerResponse();
        exceptionHandlerResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionHandlerResponse.setMessage(e.getMessage());
        exceptionHandlerResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<ExceptionHandlerResponse> exceptionHandler(ConstraintViolationException e) {
        ExceptionHandlerResponse exceptionHandlerResponse =  new ExceptionHandlerResponse();
        exceptionHandlerResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionHandlerResponse.setMessage(e.getMessage());
        exceptionHandlerResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(exceptionHandlerResponse, HttpStatus.BAD_REQUEST);
    }

}
