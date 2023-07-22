package com.raunak.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseURL {
    @GetMapping("/")
    public String redirect() {
        return "employees/home";
    }
}
