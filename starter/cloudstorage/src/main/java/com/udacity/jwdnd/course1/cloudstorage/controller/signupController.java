package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/signup")
@Controller
public class signupController {

    private userServiceImpl userService;

    public signupController(userServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signup(){
        return "signup";
    }

    @PostMapping("/add")
    public String add(@RequestParam(value="firstName", required=true) String firstname,
                      @RequestParam(value="lastName", required=true) String lastname,
                      @RequestParam(value="username", required=true) String username,
                      @RequestParam(value="password", required=true) String password,
                      Model model){

        Boolean signupResult = false;// 註冊結果

        User user = new User(null,username,null,password,firstname,lastname);
        signupResult = userService.addNewUser(user);

        if(signupResult){// 新增用戶成功
           model.addAttribute("signUpStatus",true);
        }

        return "signup";
    }

}
