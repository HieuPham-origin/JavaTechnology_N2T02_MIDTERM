package com.example.midterm.services;

import com.example.midterm.dtos.UserDTO;
import com.example.midterm.models.Customer;
import com.example.midterm.repositories.CustomerRepository;
import jakarta.transaction.Transaction;
import jakarta.transaction.Transactional;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Customer saveCustomer(UserDTO user){
        Customer customer = new Customer();
        customer.setName(user.getName());
        customer.setUsername(user.getUsername());
        customer.setPassword(passwordEncoder.encode(user.getPassword()));
        customer.setRole("ROLE_USER");
        return this.customerRepository.save(customer);
    }
    @Override
    public Customer findByUserName(String username){
        return this.customerRepository.findByUsername(username);
    }
}
