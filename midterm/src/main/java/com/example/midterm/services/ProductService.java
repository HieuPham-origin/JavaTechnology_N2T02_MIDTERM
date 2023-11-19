package com.example.midterm.services;

import com.example.midterm.models.Product;
import com.example.midterm.models.ProductImage;

import java.util.List;

public interface ProductService{
    List<Product> getAllProducts();
    Product addProduct(Product product);
    void deleteProduct(int id);
    Product updateProduct(int id, Product product);
    List<ProductImage> getImageByProductId(int id);
    ProductImage selectFirstImageOfProduct(int productId);
    List<Product> getProductsByBrand(int brandId);
    List<Product> getProductsByCategory(int categoryId);
}
