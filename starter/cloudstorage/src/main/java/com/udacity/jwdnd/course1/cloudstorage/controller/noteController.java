package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class noteController {

    @PostMapping("/note/add")
    public String logindo(String noteTitle, String noteDescription){

        return "success";

    }

}
