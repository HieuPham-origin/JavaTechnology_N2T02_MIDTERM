package com.example.midterm.services;

import com.example.midterm.models.Category;
import com.example.midterm.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories(){
        return this.categoryRepository.findAll();
    }
    @Override
    public Category addCategory(Category category){
        return this.categoryRepository.save(category);
    }
    @Override
    public Optional<Category> getCategoryById(int id){
        return this.categoryRepository.findById(id);
    }
    @Override
    public void deleteCategory(int id){
        this.categoryRepository.deleteById(id);
    }
    @Override
    public Category updateCategory(int id, Category category){
        Optional<Category> update = this.categoryRepository.findById(id);
        if(update.isPresent()){
            category.setCategoryId(id);
            return this.categoryRepository.save(category);
        }
        else{
            return null;
        }
    }
}
