package com.example.midterm.controllers;

import com.example.midterm.dtos.UserDTO;
//import com.example.midterm.services.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
//    @Autowired
//    private UserDetailService userDetailService;
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
}
