package com.expensetracker.expense_tracker.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("Leslie", "leslie@gmail.com", "test123", null, null);
    }

    @Test
    public void testDefaultConstructor() {        User defaultUser = new User();
        assertNotNull(defaultUser, "Default constructor should create a non-null object");
        assertEquals(0, defaultUser.getId(), "Default ID should be 0");
        assertNull(defaultUser.getName(), "Default name should be null");
        assertNull(defaultUser.getEmail(), "Default email should be null");
        assertNull(defaultUser.getPassword(), "Default password should be null");
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Leslie", user.getName(), "Name should be initialized to 'Leslie'");
        assertEquals("leslie@gmail.com", user.getEmail(), "Email should be initialized to 'leslie@gmail.com'");
        assertEquals("test123", user.getPassword(), "Password should be initialized to 'test123'");
    }

    @Test
    public void testSettersAndGetters() {
        user.setId(1);
        user.setName("Leslie");
        user.setEmail("leslie@gmail.com");
        user.setPassword("test123");

        assertEquals(1, user.getId(), "ID should be set to 1");
        assertEquals("Leslie", user.getName(), "Name should be set to 'Leslie'");
        assertEquals("leslie@gmail.com", user.getEmail(), "Email should be set to 'leslie@gmail.com'");
        assertEquals("test123", user.getPassword(), "Password should be set to 'test123'");
    }

    @Test
    public void testToString() {
        String expected = "User{id=0, name='Leslie', email='leslie@gmail.com', password='test123', roles=null, transactions=null}";
        assertEquals(expected, user.toString(), "toString method should return the expected string");
    }


}