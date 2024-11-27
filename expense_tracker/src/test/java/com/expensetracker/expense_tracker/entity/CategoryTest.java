package com.expensetracker.expense_tracker.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category("Books", "something that i really wanted");
    }

    @Test
    public void testDefaultConstructor() {
        Category defaultCategory = new Category();
        assertNotNull(defaultCategory, "Default constructor should create a non-null object");
        assertEquals(0, defaultCategory.getId(), "Default ID should be 0");
        assertNull(defaultCategory.getName(), "Default name should be null");
        assertNull(defaultCategory.getDescription(), "Default description should be null");
    }

    @Test
    public void testParameterizedConstructor() {
        assertEquals("Books", category.getName(), "Name should be initialized to 'Books'");
        assertEquals("something that i really wanted", category.getDescription(), "Description should match the provided value");
    }

    @Test
    public void testSettersAndGetters() {
        category.setId(1);
        category.setName("Movies");
        category.setDescription("Category for all movie-related items");

        assertEquals(1, category.getId(), "ID should be set to 1");
        assertEquals("Movies", category.getName(), "Name should be set to 'Movies'");
        assertEquals("Category for all movie-related items", category.getDescription(), "Description should match the new value");
    }

    @Test
    public void testToString() {
        String expected = "Category{id=0, name='Books', description='something that i really wanted'}";
        assertEquals(expected, category.toString(), "toString method should return the expected string");
    }

}