package com.expensetracker.expense_tracker.rest;

import com.expensetracker.expense_tracker.entity.Category;
import com.expensetracker.expense_tracker.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    private List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/category/{categoryId}")
    private Category getCategoryById(@PathVariable int categoryId) {
        Category category = categoryService.findCategoryById(categoryId);

        if (category == null) {
            throw new RuntimeException("Did not find the category id: " + categoryId);
        }
        return category;
    }

    @PostMapping("/category")
    private Category addCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PutMapping("/category")
    private Category updateCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @DeleteMapping("/category/{categoryId}")
    private void deleteCategory(@PathVariable int categoryId) {
        Category category = categoryService.findCategoryById(categoryId);

        if (category == null) {
            throw new RuntimeException("Did not find the category id: " + categoryId);
        }

        categoryService.deleteCategory(categoryId);
    }
}
