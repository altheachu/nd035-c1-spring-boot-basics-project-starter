package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class homeController {

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @PostMapping("/logout")
    public String logoutView(){
        return "redirect:/login?logout";
    }
}
