package com.expensetracker.expense_tracker.service;

import com.expensetracker.expense_tracker.dao.CategoryDAO;
import com.expensetracker.expense_tracker.entity.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryImpl implements CategoryService {

    CategoryDAO categoryDAO;

    @Autowired
    public CategoryImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDAO.findAll();
    }

    @Override
    public Category findCategoryById(int id) {
        Optional<Category> optionalCategory = categoryDAO.findById(id);

        if (optionalCategory.isPresent()) {
            return optionalCategory.get();
        }
        else throw new RuntimeException("Did not find the category id: " + id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryDAO.save(category);
    }

    @Override
    public void deleteCategory(int id) {

        if (categoryDAO.findById(id).isPresent()) {
            categoryDAO.deleteById(id);
        }
        else throw new RuntimeException("Did not find the category id: " + id);
    }
}
