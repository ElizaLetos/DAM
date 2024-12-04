package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;


public class UserDAOImpl implements UserDAOCustom{

    EntityManager entityManager;

    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public User findByUsername(String name) {
        TypedQuery<User> query = entityManager.createQuery("from User where name = :name", User.class);
        query.setParameter("name", name);
        User user = null;
        try {
            user = query.getSingleResult();
        }
        catch (Exception _) {
        }
        return user;
    }

}
