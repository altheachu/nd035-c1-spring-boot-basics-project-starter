package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("/signup")
@Controller
public class signupController {

    @GetMapping
    public String signup(){
        return "signup";
    }

    @PostMapping("/add")
    public String add(){
        System.out.println("test");
        return "signup";
    }

}
