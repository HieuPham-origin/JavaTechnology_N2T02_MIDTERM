package com.example.midterm.services;

import com.example.midterm.models.Product;
import com.example.midterm.models.ProductImage;
import com.example.midterm.repositories.ProductImageRepository;
import com.example.midterm.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductImageRepository productImageRepository;

    @Override
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }
    @Override
    public Product getProductById(int id){
        return this.productRepository.findById(id).get();
    }

    @Override
    public Product addProduct(Product product){
        return this.productRepository.save(product);
    }

    @Override
    public void deleteProduct(int id){
        this.productRepository.deleteById(id);
    }
    @Override
    public List<ProductImage> getImageByProductId(int id){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            return this.productImageRepository.findByProductId(id);
        }else{
            return null;
        }
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
    @Override
    public ProductImage selectFirstImageOfProduct(int productId){
        return this.productImageRepository.selectFirstImageOfProduct(productId);
    }
    @Override
    public List<Product> getProductsByBrand(int brandId){
        return this.productRepository.getProductsByBrand(brandId);
    }
    @Override
    public List<Product> getProductsByCategory(int categoryId){
        return this.productRepository.getProductsByCategory(categoryId);
    }
}
