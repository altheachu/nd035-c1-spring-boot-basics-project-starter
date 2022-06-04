package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class loginController {

    private userServiceImpl userService;

    public loginController(userServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/loginDo")
    public String logindo(String username, String password){

        String url = "login";

        User user = userService.getUser(username);
        if(user != null){
            if(user.getPassword().equals(password)){
                url = "home";
            }
        }
        return url;
    }

}
