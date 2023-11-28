package com.example.midterm.controllers;

import com.example.midterm.models.Product;
import com.example.midterm.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping(value = { "", "/" })
    public List<Product> getAllProducts() {
        return this.productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }
    @GetMapping(value = {"/{id}"})
    public Product getProduct(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @RequestBody Product product) throws Exception {
        Product exist = productService.getProductById(id);
        if (exist != null) {
            if (product.getProductName() != null)
                exist.setProductName(product.getProductName());
            if (product.getPrice() > 0)
                exist.setPrice(product.getPrice());
            if (product.getBrand() != null)
                exist.setBrand(product.getBrand());
            if (product.getColor() != null)
                exist.setColor(product.getColor());
            productService.updateProduct(exist.getProductId(), exist);
            return ResponseEntity.ok(exist);
        } else {
            throw new Exception("Product not found with id: " + id);
        }
    }

    @PatchMapping(value = {"/{id}"})
    public ResponseEntity<Product> modifyProduct(@PathVariable Integer id, @RequestBody Product product) throws Exception {
        Product exist = productService.getProductById(id);
        if (exist != null) {
            if (product.getProductName() != null)
                exist.setProductName(product.getProductName());
            if (product.getPrice() > 0)
                exist.setPrice(product.getPrice());
            if (product.getBrand() != null)
                exist.setBrand(product.getBrand());
            if (product.getColor() != null)
                exist.setColor(product.getColor());
            productService.updateProduct(exist.getProductId(), exist);
            return ResponseEntity.ok(exist);
        }else{
            throw new Exception("Product not found with id: " + id);
        }
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<Void> deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
