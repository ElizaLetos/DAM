package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.dao.CategoryDAO;
import com.expensetracker.expense_tracker.dao.TransactionDAO;
import com.expensetracker.expense_tracker.dao.UserDAO;
import com.expensetracker.expense_tracker.entity.Category;
import com.expensetracker.expense_tracker.entity.Transaction;
import com.expensetracker.expense_tracker.entity.TypeOfTransaction;
import com.expensetracker.expense_tracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService{

    TransactionDAO transactionDAO;

    CategoryDAO categoryDAO;

    UserDAO userDAO;

    @Autowired
    public TransactionServiceImpl(TransactionDAO transactionDAO, CategoryDAO categoryDAO, UserDAO userDAO) {
        this.transactionDAO = transactionDAO;
        this.categoryDAO = categoryDAO;
        this.userDAO = userDAO;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionDAO.findAll();
    }

    @Override
    public Transaction findTransactionById(int id) {
        Optional<Transaction> optionalTransaction = transactionDAO.findById(id);

        if (optionalTransaction.isPresent()) {
            return optionalTransaction.get();
        }
        else throw new RuntimeException("Did not find the transaction id: " + id);
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionDAO.save(transaction);
    }

    @Override
    public List<Transaction> getTransactionsByType(TypeOfTransaction typeOfTransaction, int userId) {
        return transactionDAO.getTransactionsByType(typeOfTransaction, userId);
    }

    @Override
    public int findIdByUsername(String name) {
        return transactionDAO.findIdByUsername(name);
    }

    @Override
    public List<Transaction> getTransactionsFromUser(int id) {
        return transactionDAO.getTransactionsFromUser(id);
    }

    @Override
    public Category findCategoryById(int categoryId) {
        return categoryDAO.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found with ID: " + categoryId));
    }

    @Override
    public User findUserById(int userId) {
        return userDAO.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));
    }

    @Override
    public void deleteTransaction(int id) {

        if (transactionDAO.findById(id).isPresent()) {
            transactionDAO.deleteById(id);
        }
        else throw new RuntimeException("Did not find the transaction id: " + id);
    }
}
