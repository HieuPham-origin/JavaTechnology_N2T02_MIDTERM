package com.example.midterm.services;

import com.example.midterm.models.Product;
import com.example.midterm.models.ProductImage;
import com.example.midterm.repositories.ProductImageRepository;
import com.example.midterm.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductImageServiceImpl implements ProductImageService{
    @Autowired
    private ProductImageRepository productImageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<ProductImage> getImageByProductId(int id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return this.productImageRepository.findByProductId(id);
        }else{
            return null;
        }
    }
}
