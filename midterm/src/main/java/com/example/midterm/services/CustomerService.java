package com.example.midterm.services;

import com.example.midterm.models.Customer;
import com.example.midterm.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Customer customer = customerRepository.findByUsername(username);
        if (customer == null){
            throw new UsernameNotFoundException("Customer not found with email: " + username);
        }
        return new Customer(customer.getUsername(), customer.getPassword());
    }
    public Boolean existByUserName(String username){
        return this.customerRepository.existsByUsername(username);
    }
    public String getCurrentUsername() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }
    public Customer getByUsername(String username){
        return this.customerRepository.findByUsername(username);
    }
}
