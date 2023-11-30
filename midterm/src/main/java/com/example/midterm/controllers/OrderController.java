package com.example.midterm.controllers;

import com.example.midterm.models.Order;
import com.example.midterm.models.Product;
import com.example.midterm.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping(value = { "", "/" })
    public List<Order> getAllOrders() {
        return this.orderService.getAllOrders();
    }
    @GetMapping(value = {"/{id}"})
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }
}
