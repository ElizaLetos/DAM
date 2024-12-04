package com.expensetracker.expense_tracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.expensetracker.expense_tracker.dao.CategoryDAO;
import com.expensetracker.expense_tracker.entity.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
class CategoryImplTest {

    @Mock
    private CategoryDAO categoryDAO;

    @InjectMocks
    private CategoryImpl categoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCategories() {
        List<Category> categories = List.of(new Category("Books", "something that i really wanted"));
        when(categoryDAO.findAll()).thenReturn(categories);

        List<Category> result = categoryService.getAllCategories();
        assertEquals(categories, result, "getAllCategories should return all categories");
    }

    @Test
    void findCategoryById() {
        Category categories = new Category("Books", "something that i really wanted");
        when(categoryDAO.findById(0)).thenReturn(Optional.of(categories));

        Category result = categoryService.findCategoryById(0);
        assertEquals(categories, result, "findCategoryById should return the correct category when found");
    }

    @Test
    void saveCategory() {
        Category category = new Category("Books", "something that i really wanted");
        when(categoryDAO.save(category)).thenReturn(category);

        Category result = categoryService.saveCategory(category);
        assertEquals(category, result, "saveCategory should return the saved category");
    }

    @Test
    void deleteCategory() {
        when(categoryDAO.findById(0)).thenReturn(Optional.of(new Category()));

        categoryService.deleteCategory(0);

        verify(categoryDAO, times(1)).deleteById(0);
    }
}