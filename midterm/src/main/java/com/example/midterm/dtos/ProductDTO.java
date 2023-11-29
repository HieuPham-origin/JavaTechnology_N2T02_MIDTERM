package com.example.midterm.dtos;

import com.example.midterm.models.Product;
import lombok.Data;

@Data
public class ProductDTO {
    private Product product;
    private int price;
    private int quantity;
    public ProductDTO(Product product, int price, int quantity){
        this.product = product;
        this.price = price;
        this.quantity = quantity;
    }
}
