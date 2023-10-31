package com.example.midterm.models;
import lombok.*;
import jakarta.persistence.*;

@AllArgsConstructor
@Setter @Getter
@NoArgsConstructor
@Entity
@Table(name = "order_detail")
@EqualsAndHashCode(exclude = {"product"})
public class OrderDetail {
    @EmbeddedId
    private OrderDetailId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;
}
