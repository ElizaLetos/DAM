package com.expensetracker.expense_tracker.dao;

import com.expensetracker.expense_tracker.entity.Transaction;
import com.expensetracker.expense_tracker.entity.TypeOfTransaction;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class TransactionDAOImplTest {

    private TransactionDAOImpl transactionDAO;

    @Autowired
    private EntityManager entityManager;

    @BeforeEach
    public void setUp() {
        transactionDAO = new TransactionDAOImpl(entityManager);
    }

    @Test
    @DirtiesContext
    @Sql(scripts = {"classpath:test_sql/cleanup.sql", "classpath:test_sql/create-test-data.sql"})
    void getTransactionsByType() {
        List<Transaction> transactions = transactionDAO.getTransactionsByType(TypeOfTransaction.EXPENSE, 1);

        assertNotNull(transactions, "Transactions should not be null");
        assertFalse(transactions.isEmpty(), "Transactions should not be empty");
        transactions.forEach(transaction -> assertEquals(TypeOfTransaction.EXPENSE, transaction.getTypeOfTransaction(),
                "Each transaction should match the specified type"));
    }

    @Test
    @DirtiesContext
    @Sql(scripts = {"classpath:test_sql/cleanup.sql", "classpath:test_sql/create-test-data.sql"})
    void findIdByUsername() {
        int userId = transactionDAO.findIdByUsername("Leslie");

        assertEquals(1, userId, "User ID should match the expected ID");
    }

    @Test
    @DirtiesContext
    @Sql(scripts = {"classpath:test_sql/cleanup.sql", "classpath:test_sql/create-test-data.sql"})
    public void testFindIdByUsername_UserNotFound() {

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionDAO.findIdByUsername("nonexistentUser");
        });

        assertTrue(exception.getMessage().contains("An error occurred while finding the user"),
                "Exception message should indicate an error in finding the user");
    }

    @Test
    @DirtiesContext
    @Sql(scripts = {"classpath:test_sql/cleanup.sql", "classpath:test_sql/create-test-data.sql"})
    void getTransactionsFromUser() {
        List<Transaction> transactions = transactionDAO.getTransactionsFromUser(1);

        assertNotNull(transactions, "Transactions should not be null");
        assertFalse(transactions.isEmpty(), "Transactions should not be empty");
        transactions.forEach(transaction -> assertEquals(1, transaction.getUser().getId(),
                "Each transaction should belong to the specified user"));
    }
}