package com.expense_tracker.Expense.Tracker.rest;

import com.expense_tracker.Expense.Tracker.model.UserDetails;
import com.expense_tracker.Expense.Tracker.model.UserRequestDTO;
import com.expense_tracker.Expense.Tracker.service.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    User user;

    @Autowired
    public UserController(User user) {
        this.user = user;
    }

    @PostMapping("/user")
    public void saveUser(@RequestBody UserRequestDTO userRequestDTO) {
        UserDetails userDetails = new UserDetails();
        userDetails.setUserName(userRequestDTO.userName());
        userDetails.setPassword(userRequestDTO.password());
        userDetails.setFirstName(userRequestDTO.firstName());
        userDetails.setLastName(userRequestDTO.lastName());
        user.saveUser(userDetails);
    }

    @PutMapping("/user")
    public void modifyUser(@RequestBody UserDetails userDetails) {
        user.modifyUserDetails(userDetails);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable int userId) {
        user.deleteUser(userId);
    }

    @GetMapping("/user/{userId}")
    public UserDetails getUser(@PathVariable int userId) {
        return user.getUser(userId);
    }

}
