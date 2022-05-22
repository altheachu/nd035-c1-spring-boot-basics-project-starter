package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("signup")
public class signupController {

    @GetMapping
    public String signup(){
        return "signup";
    }

}
