package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getAllUsers();

    User findUserById(int id);

    User saveUser(User user);

    void deleteUser(int id);
}
