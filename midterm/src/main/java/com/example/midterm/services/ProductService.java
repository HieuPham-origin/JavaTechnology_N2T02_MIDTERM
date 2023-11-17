package com.example.midterm.services;

import com.example.midterm.models.Product;

import java.util.List;

public interface ProductService{
    List<Product> getAllProducts();
    Product createProduct(Product product);
    void deleteProduct(int id);
    Product updateProduct(int id, Product product);
}
