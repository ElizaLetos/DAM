package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.User;

public interface UserDAOCustom {

    User findByUsername(String name);

}
