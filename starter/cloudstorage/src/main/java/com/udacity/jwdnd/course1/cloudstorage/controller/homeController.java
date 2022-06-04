package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class homeController {

    @GetMapping
    public String homePage(){
        return "home";
    }

    @PostMapping("/logout")
    public String backLogin(Model model){
        model.addAttribute("logOutStatus",true);
        return "login";
    }

}
