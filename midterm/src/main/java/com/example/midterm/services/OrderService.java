package com.example.midterm.services;

import com.example.midterm.models.Order;
import com.example.midterm.models.OrderDetail;
import com.example.midterm.models.OrderDetailId;
import com.example.midterm.models.Product;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(int orderId);
    Order checkOut(Order order);
    void deleteOrder(int id);
    Order updateOrder(int id, Order order);
    void addToCart(Product product, Order order, int quantity);
    Optional<Order> existOrder(String username);
    List<Product> listItems(int orderId);
//    Integer totalPrice(Order order);
}
