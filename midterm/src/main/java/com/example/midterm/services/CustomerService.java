package com.example.midterm.services;

import com.example.midterm.dtos.UserDTO;
import com.example.midterm.models.Customer;

import java.util.List;

public interface CustomerService {
    Customer saveCustomer(UserDTO user);
    Customer findByUserName(String username);
}
