package com.expensetracker.expense_tracker.rest;

import com.expensetracker.expense_tracker.entity.User;
import com.expensetracker.expense_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/user/{userId}")
    public User getUserById(@PathVariable int userId) {
        User user = userService.findUserById(userId);

        if (user == null) {
            throw new RuntimeException("Did not find the id: " + userId);
        }
        return user;
    }

    @PutMapping("/user")
    public User updateUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @PostMapping("/user")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable int userId) {

        if (userService.findUserById(userId) == null) {
            throw new RuntimeException("Did not find the id: " + userId);
        }

        userService.deleteUser(userId);
    }
}
