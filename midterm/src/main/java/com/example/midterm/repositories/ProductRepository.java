package com.example.midterm.repositories;
import com.example.midterm.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT S FROM Product S WHERE S.price >= ?1")
    List<Product> findByPriceUpper(int price);

    @Query("SELECT S FROM Product S WHERE S.price <= ?1")
    List<Product> findByPriceLower(int price);

    @Query("SELECT S FROM Product S WHERE S.price >= ?1 AND S.price <= ?2")
    List<Product> findByPriceRange(int lower, int upper);

    @Query("SELECT P FROM Product P WHERE P.brand.brandId = ?1")
    List<Product> getProductsByBrand(int brandId);

    @Query("SELECT P FROM Product P WHERE P.category.categoryId = ?1")
    List<Product> getProductsByCategory(int categoryId);
    @Query("SELECT P FROM Product P WHERE P.color = ?1")
    List<Product> getProductsByColor(String color);
    @Query("SELECT DISTINCT P.color FROM Product P")
    List<String> getColors();
}
