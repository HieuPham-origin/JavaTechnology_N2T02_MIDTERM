package com.example.midterm.repositories;

import com.example.midterm.models.Product;
import com.example.midterm.models.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    @Query("SELECT S FROM ProductImage S WHERE S.product.productId >= ?1")
    List<ProductImage> findByProductId(int id);
    @Query("SELECT pi FROM ProductImage pi WHERE pi.product.productId = ?1 ORDER BY pi.id ASC")
    ProductImage selectFirstImageOfProduct(int productId);
}
