package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getAllCategories();

    Category findCategoryById(int id);

    Category saveCategory(Category category);

    void deleteCategory(int id);
}
