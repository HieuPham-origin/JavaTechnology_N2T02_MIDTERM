package com.example.midterm.services;

import com.example.midterm.models.Product;
import com.example.midterm.models.ProductImage;

import java.util.List;
import java.util.Optional;

public interface ProductService{
    List<Product> getAllProducts();
    Product getProductById(int id);
    Product addProduct(Product product);
    void deleteProduct(int id);
    Product updateProduct(int id, Product product);
    List<ProductImage> getImageByProductId(int id);
    ProductImage selectFirstImageOfProduct(int productId);
    List<Product> getProductsByBrand(int brandId);
    List<Product> getProductsByCategory(int categoryId);
    List<Product> getProductsByColor(String color);
    List<String> getColors();
}
