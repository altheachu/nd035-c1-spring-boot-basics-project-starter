package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class loginController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginDo")
    public String logindo(String username, String password){
        System.out.println("test");
        return "home";
    }

}
