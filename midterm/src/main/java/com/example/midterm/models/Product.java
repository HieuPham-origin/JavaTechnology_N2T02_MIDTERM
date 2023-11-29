package com.example.midterm.models;
import lombok.*;
import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "product")

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private int price;

    @Column(name = "description")
    private String description;
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
