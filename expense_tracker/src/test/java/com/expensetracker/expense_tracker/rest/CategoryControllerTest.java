package com.expensetracker.expense_tracker.rest;

import com.expensetracker.expense_tracker.entity.Category;
import com.expensetracker.expense_tracker.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CategoryControllerTest {
    private MockMvc mockMvc;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(categoryController).build();
    }

    @Test
    void getAllCategories() throws Exception {
        Category category = new Category("gift", "my anniversary");

        when(categoryService.getAllCategories()).thenReturn(Arrays.asList(category));

        mockMvc.perform(get("/api/category")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("gift"))
                .andExpect(jsonPath("$[0].description").value("my anniversary"));
    }

    @Test
    void getCategoryById() throws Exception {
        Category category = new Category("gift", "my anniversary");

        when(categoryService.findCategoryById(0)).thenReturn(category);

        mockMvc.perform(get("/api/category/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("gift"))
                .andExpect(jsonPath("$.description").value("my anniversary"));
    }

    @Test
    void updateCategory() throws Exception {
        Category category = new Category("Gift", "my anniversary");

        when(categoryService.saveCategory(any(Category.class))).thenReturn(category);

        mockMvc.perform(put("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 0, \"name\": \"Gift\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Gift"))
                .andExpect(jsonPath("$.description").value("my anniversary"));
    }

    @Test
    void addCategory() throws Exception {
        Category category = new Category("gift", "my anniversary");

        when(categoryService.saveCategory(any(Category.class))).thenReturn(category);

        mockMvc.perform(post("/api/category")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 0, \"name\": \"gift\", \"description\": \"my anniversary\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(0))
                .andExpect(jsonPath("$.name").value("gift"))
                .andExpect(jsonPath("$.description").value("my anniversary"));
    }

    @Test
    void deleteCategory() throws Exception {
        Category category = new Category("gift", "my anniversary");

        when(categoryService.findCategoryById(0)).thenReturn(category);
        doNothing().when(categoryService).deleteCategory(0);

        mockMvc.perform(delete("/api/category/0")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}