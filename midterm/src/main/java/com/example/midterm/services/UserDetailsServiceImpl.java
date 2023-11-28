package com.example.midterm.services;

import com.example.midterm.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private CustomerService customerService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Customer customer = customerService.findByUserName(username);
        if (customer == null){
            throw new UsernameNotFoundException("Customer not found with email: " + username);
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(customer.getUsername()).password(customer.getPassword())
                .roles(customer.getRole()).build();
    }
}
