package com.raunak.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "employees/login";
    }
    @GetMapping("/access-denied")
    public String showMyAccessDeniedPage() {
        return "employees/access-denied";
    }
}
