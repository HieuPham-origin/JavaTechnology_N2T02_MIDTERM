package com.example.midterm.repositories;
import com.example.midterm.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT S FROM Product S WHERE S.productId >= ?1")
    List<Product> findByAge(int id);

    @Query("SELECT S FROM Product S WHERE S.price >= ?1")
    List<Product> findByPriceUpper(int price);

    @Query("SELECT S FROM Product S WHERE S.price <= ?1")
    List<Product> findByPriceLower(int price);
}
