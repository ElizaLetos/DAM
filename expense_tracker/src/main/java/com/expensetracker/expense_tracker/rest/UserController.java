package com.expensetracker.expense_tracker.rest;

import com.expensetracker.expense_tracker.entity.Role;
import com.expensetracker.expense_tracker.entity.User;
import com.expensetracker.expense_tracker.entity.UserResponse;
import com.expensetracker.expense_tracker.entity.UserRole;
import com.expensetracker.expense_tracker.service.UserRoleService;
import com.expensetracker.expense_tracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    public UserController(UserService userService, UserRoleService userRoleService) {

        this.userService = userService;
        this.userRoleService = userRoleService;
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
    public UserResponse addUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);

        UserRole userRole = new UserRole();
        userRole.setUserId(savedUser.getId());
        userRole.setRole(Role.ROLE_USER);
        userRoleService.saveUserRole(userRole);

        user.setRoles(List.of(Role.ROLE_USER));

        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setToken("mock-jwt-token-for-" + user.getName());
        userResponse.setRoles(user.getRoles());

        return userResponse;
    }

    @DeleteMapping("/user/{userId}")
    public void deleteUser(@PathVariable int userId) {

        if (userService.findUserById(userId) == null) {
            throw new RuntimeException("Did not find the id: " + userId);
        }

        userService.deleteUser(userId);
    }
}
