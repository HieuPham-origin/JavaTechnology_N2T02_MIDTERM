package com.example.midterm.repositories;

import com.example.midterm.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findByUsername(String username);
    Boolean existsByUsername(String username);
}
