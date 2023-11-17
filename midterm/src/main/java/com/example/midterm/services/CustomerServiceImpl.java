package com.example.midterm.services;

import com.example.midterm.models.Customer;
import com.example.midterm.repositories.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    }
}
