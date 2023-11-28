package com.example.midterm.controllers;

import com.example.midterm.dtos.UserDTO;
//import com.example.midterm.services.UserDetailService;
import com.example.midterm.models.Customer;
import com.example.midterm.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthenticationController {
//    @Autowired
    private CustomerService customerService;
    public AuthenticationController(CustomerService customerService){
        this.customerService = customerService;
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/register")
    public String register(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("customer", userDTO);
        return "register";
    }
    @PostMapping("/register/save")
    public String registration(@Valid @ModelAttribute("customer") UserDTO user,
                               BindingResult result,
                               Model model){
        Customer existing = customerService.findByUserName(user.getUsername());
        if (existing != null) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            model.addAttribute("user", user);
            return "register";
        }
        customerService.saveCustomer(user);
        return "redirect:/register?success";
    }
}
