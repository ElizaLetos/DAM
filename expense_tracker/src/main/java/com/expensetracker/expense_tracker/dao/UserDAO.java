package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer>, UserDAOCustom {
}
