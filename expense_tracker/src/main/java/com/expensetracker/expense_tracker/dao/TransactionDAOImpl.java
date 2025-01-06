package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.Transaction;
import com.expensetracker.expense_tracker.entity.TypeOfTransaction;
import com.expensetracker.expense_tracker.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class TransactionDAOImpl implements TransactionDAOCustom {

    EntityManager entityManager;

    public TransactionDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Transaction> getTransactionsByType(TypeOfTransaction type_of_transaction, int userId) {
        TypedQuery<Transaction> query = entityManager.createQuery("from Transaction t where t.user.id = :userId and t.typeOfTransaction = :type_of_transaction", Transaction.class);
        query.setParameter("userId", userId);
        query.setParameter("type_of_transaction", type_of_transaction);
        List<Transaction> transaction = new ArrayList<>();
        try {
            transaction = query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }

    @Override
    public int findIdByUsername(String name) {
        TypedQuery<User > query = entityManager.createQuery("from User where name = :name", User.class);
        query.setParameter("name", name);
        try {
            return query.getSingleResult().getId();
        }
        catch (Exception e) {
            throw new RuntimeException("An error occurred while finding the user", e);
        }
    }

    @Override
    public List<Transaction> getTransactionsFromUser(int id) {
        TypedQuery<Transaction> query = entityManager.createQuery("from Transaction t where t.user.id = :id", Transaction.class);
        query.setParameter("id", id);
        try {
            return query.getResultList();
        }
        catch (Exception e) {
            throw new RuntimeException("An error occurred while finding the user's transactions", e);
        }
    }

    @Override
    public List<Transaction> getTypeOfTransactionsFromCategory(int categoryId, int userId, TypeOfTransaction type_of_transaction) {
        TypedQuery<Transaction> query = entityManager.createQuery("from Transaction t where t.user.id = :userId and t.category.id = :categoryId and t.typeOfTransaction = :type_of_transaction", Transaction.class);
        query.setParameter("userId", userId);
        query.setParameter("categoryId", categoryId);
        query.setParameter("type_of_transaction", type_of_transaction);
        List<Transaction> transaction = new ArrayList<>();
        try {
            transaction = query.getResultList();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return transaction;
    }
}
