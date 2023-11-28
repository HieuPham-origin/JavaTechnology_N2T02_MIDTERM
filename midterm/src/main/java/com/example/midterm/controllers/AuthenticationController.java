package com.example.midterm.controllers;

import com.example.midterm.dtos.UserDTO;
import com.example.midterm.models.Customer;
import com.example.midterm.repositories.CustomerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private CustomerRepository customerRepository;
    public AuthenticationController(UserDetailsService userDetailsService){
        this.userDetailsService = userDetailsService;
    }
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(){
        return "register";
    }
    @PostMapping("/register")
    public ModelAndView register(@ModelAttribute UserDTO user){
        boolean existing = customerRepository.existsByUsername(user.getUsername());
        if (existing) {
            ModelAndView modelAndView = new ModelAndView("redirect:/register");
            modelAndView.addObject("error","Email was taken");
            return modelAndView;
        }
        Customer customer = new Customer();
        customer.setUsername(user.getUsername());
        customer.setName(user.getName());
        customer.setPassword(passwordEncoder.encode(user.getPassword()));
        customer.setAddress(user.getAddress());
        customer.setRole("user");
        customer.setEnabled(true);
        customerRepository.save(customer);
        return new ModelAndView("redirect:/login");
    }
}
