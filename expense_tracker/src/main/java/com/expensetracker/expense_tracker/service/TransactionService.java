package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.entity.Category;
import com.expensetracker.expense_tracker.entity.Transaction;
import com.expensetracker.expense_tracker.entity.TypeOfTransaction;
import com.expensetracker.expense_tracker.entity.User;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions();

    Transaction findTransactionById(int id);

    Transaction saveTransaction(Transaction transaction);

    void deleteTransaction(int id);

    List<Transaction> getTransactionsByType(TypeOfTransaction typeOfTransaction, int userId);

    int findIdByUsername(String name);

    List<Transaction> getTransactionsFromUser(int id);

    Category findCategoryById(int categoryId);

    User findUserById(int userId);
}
