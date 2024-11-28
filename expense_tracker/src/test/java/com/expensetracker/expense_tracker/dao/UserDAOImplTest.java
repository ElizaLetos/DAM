package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.User;
import static org.junit.jupiter.api.Assertions.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Transactional
class UserDAOImplTest {

    private UserDAOImpl userDAO;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAOImpl(entityManager);
    }

    @Test
    @Sql("classpath:test_sql/create-test-data.sql")
    void findByUsername() {
        User user = userDAO.findByUsername("Leslie");

        assertNotNull(user, "User should not be null");
        assertEquals("Leslie", user.getName(), "User name should match");
        assertEquals("leslie@gmail.com", user.getEmail(), "User email should match");
        assertEquals("$2a$12$kAafc0OInCawdTNwJ/R/5.xMP5ZjvGoTob.rE3oavKiLn2t4kviQ.", user.getPassword(), "User password should match");
    }

    @Test
    @Sql("classpath:test_sql/create-test-data.sql")
    void findByUsername_WhenUserDoesNotExist() {
        User user = userDAO.findByUsername("NonExistentUser");

        assertNull(user, "User should be null when username does not exist");
    }
}