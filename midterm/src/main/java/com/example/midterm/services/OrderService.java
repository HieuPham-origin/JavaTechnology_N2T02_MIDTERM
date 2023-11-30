package com.example.midterm.services;

import com.example.midterm.dtos.ProductDTO;
import com.example.midterm.models.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(int orderId);
    void deleteOrder(int id);
    Order updateOrder(int id, Order order);
    void addToCart(Product product, Order order, int quantity);
    Optional<Order> existOrder(String username);
    List<ProductDTO> listItems(int orderId);
    int totalPrice(Order order);
    void createCart(String username);
}
