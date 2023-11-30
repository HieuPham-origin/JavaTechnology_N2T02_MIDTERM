package com.example.midterm.services;

import com.example.midterm.dtos.ProductDTO;
import com.example.midterm.models.*;
import com.example.midterm.repositories.CustomerRepository;
import com.example.midterm.repositories.OrderDetailRepository;
import com.example.midterm.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }
    @Override
    public Order getOrderById(int orderId){return this.orderRepository.findById(orderId).get();}
    @Override
    public Order checkOut(Order order){
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
    public void addToCart(Product product, Order order, int quantity){
        Optional<OrderDetail> exist = orderDetailRepository.existOrder(order.getOrderId(),
                product.getProductId());
        if(exist.isPresent()){
            OrderDetail update = exist.get();
            update.setPrice(exist.get().getPrice() + product.getPrice()*quantity);
            update.setQuantity(exist.get().getQuantity()+quantity);
            orderDetailRepository.save(update);
        }else{
            OrderDetail orderDetail = new OrderDetail();
            OrderDetailId orderDetailId = new OrderDetailId(product.getProductId(), order.getOrderId());
            orderDetail.setId(orderDetailId);
            orderDetail.setQuantity(quantity);
            orderDetail.setPrice(product.getPrice()*quantity);
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetailRepository.save(orderDetail);
        }
    }
    @Override
    public Optional<Order> existOrder(String username){
        return this.orderRepository.findOrder(username);
    }
    @Override
    public List<ProductDTO> listItems(int orderId){
        return this.orderDetailRepository.listItems(orderId);
    }
    @Override
    public int totalPrice(Order order){
        return this.orderDetailRepository.total(order.getOrderId());
    }
    public void createCart(String username){
        Optional<Order> order = orderRepository.findOrder(username);
        Customer customer = customerRepository.findByUsername(username);
        if(order.isEmpty()){
            LocalDate currentDate = LocalDate.now();
            Order newOrder = new Order();
            newOrder.setCustomer(customer);
            newOrder.setPrice(0);
            newOrder.setStatus("Pending");
            newOrder.setPayment("COD");
            newOrder.setDayCreated(currentDate);
            orderRepository.save(newOrder);
        }
    }
    public OrderDetail upOne(Product product, Order order){
        OrderDetail orderDetail = orderDetailRepository.getItem(product.getProductId(), order.getOrderId());
        orderDetail.setQuantity(orderDetail.getQuantity()+1);
        orderDetail.setPrice(orderDetail.getPrice()+product.getPrice());
        orderDetailRepository.save(orderDetail);
        return orderDetail;
    }
    public OrderDetail downOne(Product product, Order order){
        OrderDetail orderDetail = orderDetailRepository.getItem(product.getProductId(), order.getOrderId());
        orderDetail.setQuantity(orderDetail.getQuantity()-1);
        orderDetail.setPrice(orderDetail.getPrice()-product.getPrice());
        orderDetailRepository.save(orderDetail);
        return orderDetail;
    }
    @Override
    public Order checkout(Order order){
        orderRepository.save(order);
        return order;
    }
}
