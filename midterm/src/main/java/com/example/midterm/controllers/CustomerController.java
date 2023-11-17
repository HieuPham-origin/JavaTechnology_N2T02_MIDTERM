package com.example.midterm.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {
    @GetMapping("index")
    public String index(){
        return "index";
    }
    @GetMapping("/shop")
    public String shop(){
        return "shop";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
