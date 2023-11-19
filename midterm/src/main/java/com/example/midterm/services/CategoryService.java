package com.example.midterm.services;

import com.example.midterm.models.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> getAllCategories();
    Category addCategory(Category category);
    Optional<Category> getCategoryById(int id);
    void deleteCategory(int id);
    Category updateCategory(int id, Category category);
}
