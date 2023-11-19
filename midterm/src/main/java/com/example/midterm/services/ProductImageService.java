package com.example.midterm.services;

import com.example.midterm.models.ProductImage;

import java.util.List;

public interface ProductImageService {
    List<ProductImage> getImageByProductId(int id);
}
