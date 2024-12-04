package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.dao.TransactionDAO;
import com.expensetracker.expense_tracker.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceImplTest {

    @Mock
    private TransactionDAO transactionDAO;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    User user;

    Category category;

    Date date = Date.from(Instant.now());

    Transaction transaction;

    List<Transaction> transactions;

    @BeforeEach
    public void setUp() {
        user = new User("Leslie", "leslie@gmail.com", "test123");

        category = new Category("gift", "first year anniversary");

        transaction = new Transaction(user, category, 1000D, PaymentType.CASH, date, null, TypeOfTransaction.EXPENSE);

        transactions = List.of(transaction);

        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllTransactions() {
        when(transactionDAO.findAll()).thenReturn(transactions);

        List<Transaction> result = transactionService.getAllTransactions();
        assertEquals(transactions, result, "getAllTransactions should return all transactions");
    }

    @Test
    void findTransactionById() {
        when(transactionDAO.findById(0)).thenReturn(Optional.of(transaction));

        Transaction result = transactionService.findTransactionById(0);
        assertEquals(transaction, result, "findTransactionById should return the correct transaction when found");
    }

    @Test
    void saveTransaction() {
        when(transactionDAO.save(transaction)).thenReturn(transaction);

        Transaction result = transactionService.saveTransaction(transaction);
        assertEquals(transaction, result, "saveTransaction should return the saved transaction");
    }

    @Test
    void getTransactionsByType() {
        TypeOfTransaction type = TypeOfTransaction.EXPENSE;
        when(transactionDAO.getTransactionsByType(type, 0)).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactionsByType(type, 0);
        assertEquals(transactions, result, "getTransactionsByType should return the correct list of transactions");
    }

    @Test
    void findIdByUsername() {
        when(transactionDAO.findIdByUsername("Leslie")).thenReturn(0);

        int result = transactionService.findIdByUsername("Leslie");
        assertEquals(0, result, "findIdByUsername should return the correct user ID");
    }

    @Test
    void getTransactionsFromUser() {
        when(transactionDAO.getTransactionsFromUser(0)).thenReturn(transactions);

        List<Transaction> result = transactionService.getTransactionsFromUser(0);
        assertEquals(transactions, result, "getTransactionsFromUser should return the correct list of transactions");
    }

    @Test
    void deleteTransaction() {
        when(transactionDAO.findById(0)).thenReturn(Optional.of(new Transaction()));

        transactionService.deleteTransaction(0);

        verify(transactionDAO, times(1)).deleteById(0);
    }
    @Test
    public void deleteTransaction_NotFound() {
        when(transactionDAO.findById(0)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            transactionService.deleteTransaction(0);
        });

        assertEquals("Did not find the transaction id: " + 0, exception.getMessage(), "Exception message should match when trying to delete a non-existent transaction");
    }

}