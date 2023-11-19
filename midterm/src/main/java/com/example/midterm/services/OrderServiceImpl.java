package com.example.midterm.services;

import com.example.midterm.models.Order;
import com.example.midterm.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }
    @Override
    public Order addOrder(Order order){
        return this.orderRepository.save(order);
    }

    @Override
    public void deleteOrder(int id){
        this.orderRepository.deleteById(id);
    }
    @Override
    public Order updateOrder(int id, Order order){
        Optional<Order> update = this.orderRepository.findById(id);
        if (update.isPresent()){
            order.setOrderId(id);
            return this.orderRepository.save(order);
        }else{
            return null;
        }
    }
}
