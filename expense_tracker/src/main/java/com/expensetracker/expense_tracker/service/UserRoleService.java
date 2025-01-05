package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.dao.UserRoleDAO;
import com.expensetracker.expense_tracker.entity.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleService {

    @Autowired
    private UserRoleDAO userRoleDAO;

    public void saveUserRole(UserRole userRole) {
        userRoleDAO.save(userRole);
    }
}
