package com.example.midterm.services;

import com.example.midterm.models.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order addOrder(Order order);
    void deleteOrder(int id);
    Order updateOrder(int id, Order order);
}
