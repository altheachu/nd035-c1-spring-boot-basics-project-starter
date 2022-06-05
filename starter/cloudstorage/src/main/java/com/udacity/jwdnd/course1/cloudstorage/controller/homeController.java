package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.noteServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.userServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class homeController {

    noteServiceImpl noteService;
    userServiceImpl userService;

    public homeController(noteServiceImpl noteService, userServiceImpl userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping
    public String homePage(Model model, Authentication authentication){

        User user = userService.getUser(authentication.getName());
        model.addAttribute("clientNotes",noteService.getNote(user.getUserId()));

        return "home";
    }

    @PostMapping("/logout")
    public String backLogin(Model model){
        model.addAttribute("logOutStatus",true);
        return "login";
    }

}
