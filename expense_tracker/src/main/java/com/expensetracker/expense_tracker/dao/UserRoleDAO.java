package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleDAO extends JpaRepository<UserRole, Integer> {
}
