package com.example.midterm.services;

import com.example.midterm.models.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {
    List<Brand> getAllBrands();
    Brand addBrand(Brand brand);
    Optional<Brand> getBrandById(int id);
    void deleteBrand(int id);
    Brand updateBrand(int id, Brand brand);
}