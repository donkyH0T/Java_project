package com.example.stamp_shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BasketController {
    @GetMapping("/basket")
    public String basket(){
        return "basket";
    }
}
