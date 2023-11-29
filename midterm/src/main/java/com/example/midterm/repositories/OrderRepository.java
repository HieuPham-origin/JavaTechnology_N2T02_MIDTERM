package com.example.midterm.repositories;

import com.example.midterm.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Query("SELECT O FROM Order O WHERE O.customer.username = ?1 and O.status = 'Pending'")
    public Optional<Order> findOrder(String username);
    public int countByCustomerCustomerId(int customerId);
}
