package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDAO extends JpaRepository<Transaction, Integer>, TransactionDAOCustom {
}
