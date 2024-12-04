package com.expensetracker.expense_tracker.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Transaction transaction;

    Date date = Date.from(Instant.now());

    User user;

    Category category;

    @BeforeEach
    public void setUp() {
        user = new User("Leslie", "leslie@gmail.com", "test123");

        category = new Category("gift", "first year anniversary");

        transaction = new Transaction(user, category, 1000D, PaymentType.CASH, date, null, TypeOfTransaction.EXPENSE);
    }

    @Test
    public void testDefaultConstructor() {
        Transaction defaultTransaction = new Transaction();
        assertNotNull(defaultTransaction, "Default constructor should create a non-null object");
        assertEquals(0, defaultTransaction.getId(), "Default ID should be 0");
        assertNull(defaultTransaction.getUser(), "Default user should be null");
        assertNull(defaultTransaction.getCategory(), "Default category should be null");
        assertNull(defaultTransaction.getAmount(), "Default amount should be null");
        assertNull(defaultTransaction.getPaymentType(), "Default payment type should be null");
        assertNull(defaultTransaction.getDate(), "Default date should be null");
        assertNull(defaultTransaction.getNote(), "Default note type should be null");
        assertNull(defaultTransaction.getTypeOfTransaction(), "Default type of transaction should be null");
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals(user.toString(), transaction.getUser().toString(), "User should be initialized to 'User{id=0, name='Leslie', email='leslie@gmail.com', password='test123', roles=null, transactions=null}'");
        assertEquals(category.toString(), transaction.getCategory().toString(), "Category should be initialized to 'Category{id=0, name='gift', description='first year anniversary'}'");
        assertEquals(1000D, transaction.getAmount(), "Amount should be initialized to 1000D");
        assertEquals(PaymentType.CASH, transaction.getPaymentType(), "Payment type should be initialized to 'CASH'");
        assertEquals(date, transaction.getDate(), "Date should be initialized to the local date");
        assertEquals(null, transaction.getNote(), "Note should be initialized to null");
        assertEquals(TypeOfTransaction.EXPENSE, transaction.getTypeOfTransaction(), "Note should be initialized to 'EXPENSE'");
    }

    @Test
    public void testSettersAndGetters() {
        transaction.setId(0);
        transaction.setUser(user);
        transaction.setCategory(category);
        transaction.setAmount(1000D);
        transaction.setPaymentType(PaymentType.CASH);
        transaction.setDate(date);
        transaction.setNote(null);
        transaction.setTypeOfTransaction(TypeOfTransaction.EXPENSE);

        assertEquals(0, transaction.getId(), "ID should be set to 0");
        assertEquals(user.toString(), transaction.getUser().toString(), "Name should be set to 'User{id=0, name='Leslie', email='leslie@gmail.com', password='test123', roles=null, transactions=null}'");
        assertEquals(category.toString(), transaction.getCategory().toString(), "Email should be set to 'Category{id=0, name='gift', description='first year anniversary'}'");
        assertEquals(1000D, transaction.getAmount(), "Amount should be set to 1000D");
        assertEquals(PaymentType.CASH, transaction.getPaymentType(), "Payment type should be set to 'CASH'");
        assertEquals(date, transaction.getDate(), "Date should be set to the local date");
        assertEquals(null, transaction.getNote(), "Note should be set to null");
        assertEquals(TypeOfTransaction.EXPENSE, transaction.getTypeOfTransaction(), "Type of transaction should be set to 'EXPENSE'");
    }

    @Test
    public void testToString() {
        String expected = String.format(
                "Transaction{id=0, user=0, category=Category{id=0, name='gift', description='first year anniversary'}, amount=1000.0, paymentType='CASH', date=%s, note='null', typeOfTransaction=EXPENSE}", date
        );
        assertEquals(expected, transaction.toString(), "toString method should return the expected string");
    }
}