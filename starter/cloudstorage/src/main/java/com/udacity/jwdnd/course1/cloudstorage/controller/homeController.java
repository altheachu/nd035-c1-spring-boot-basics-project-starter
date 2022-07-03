package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.credentialServiceImpl;
import com.udacity.jwdnd.course1.cloudstorage.services.fileServiceImpl;
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

    private final noteServiceImpl noteService;
    private final userServiceImpl userService;

    private final fileServiceImpl fileService;

    private final credentialServiceImpl credentialService;

    public homeController(noteServiceImpl noteService, userServiceImpl userService, fileServiceImpl fileService, credentialServiceImpl credentialService) {
        this.noteService = noteService;
        this.userService = userService;
        this.fileService = fileService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String homePage(Model model, Authentication authentication){

        User user = userService.getUser(authentication.getName());
        model.addAttribute("clientNotes",noteService.getAllNote(user.getUserId()));
        model.addAttribute("listOfFiles",fileService.listAll(user.getUserId()));
        model.addAttribute("listOfCredentials",credentialService.getAllCredential(user.getUserId()));
        return "home";
    }

    @PostMapping("/logout")
    public String backLogin(Model model){
        model.addAttribute("logOutStatus",true);
        return "login";
    }

}
