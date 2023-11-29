package com.example.midterm.controllers;

import com.example.midterm.dtos.UserDTO;
import com.example.midterm.models.Customer;
import com.example.midterm.repositories.CustomerRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuthenticationController {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private AuthenticationManager authenticationManager;
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
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute UserDTO user){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            HttpSession session = request.getSession();
            session.setAttribute("username", user.getUsername());
            return new ModelAndView("redirect:/index");
        }catch (AuthenticationException e){
            ModelAndView modelAndView = new ModelAndView("redirect:/login");
            modelAndView.addObject("error", "Invalid username or password");
            return modelAndView;
        }
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
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        SecurityContextHolder.clearContext();
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
