package com.example.midterm.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@NoArgsConstructor
public class ProductDTO {
    private int product_id;
    private String brand;
    private int price;
    private String product_name;
    private String color;
    @Builder
    public ProductDTO (int id, String brand, int price,String product_name,  String color){
        this.product_id = id;
        this.brand = brand;
        this.price = price;
        this.product_name = product_name;
        this.color = color;
    }
}
