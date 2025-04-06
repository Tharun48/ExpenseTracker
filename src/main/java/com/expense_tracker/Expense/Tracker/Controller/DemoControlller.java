package com.expense_tracker.Expense.Tracker.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControlller {

    @GetMapping("/demo")
    public String demo() {
        return "Hello";
    }

}
