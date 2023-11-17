package com.example.midterm.services;

import com.example.midterm.models.Product;
import com.example.midterm.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(Product product){
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id){
        this.productRepository.deleteById(id);
    }

    @Override
    public Product updateProduct(int id, Product product){
        Optional<Product> optional = this.productRepository.findById(id);
        if (optional.isPresent()){
            product.setProductId(id);
            return this.productRepository.save(product);
        }
        else{
            return null;
        }
    }
}
